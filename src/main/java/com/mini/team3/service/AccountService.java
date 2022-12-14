package com.mini.team3.service;

import com.mini.team3.dto.request.AccountRequestDto;
import com.mini.team3.dto.request.EmailRequestDto;
import com.mini.team3.dto.request.LoginRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.MyPage;
import com.mini.team3.entity.RefreshToken;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.jwt.dto.TokenDto;
import com.mini.team3.jwt.util.JwtUtil;
import com.mini.team3.repository.AccountRepository;
import com.mini.team3.repository.MypageRepository;
import com.mini.team3.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public GlobalResponseDto signup(AccountRequestDto accountRequestDto) {

        if (accountRepository.findByEmail(accountRequestDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.AlreadyHaveEmail);
        }

        // 비밀번호 일치 확인
        String password = accountRequestDto.getAccountPw();
        if(!password.equals(accountRequestDto.getAccountPwConfirm())){
            throw new CustomException(ErrorCode.NotMatchPassword);
        }

        // 비밀번호 암호화해서 저장(법으로 정해져 있음)
        accountRequestDto.setEncodePwd(passwordEncoder.encode(accountRequestDto.getAccountPw()));

        Account account = new Account(accountRequestDto);
        account.setMyPage(new MyPage(account));

        accountRepository.save(account);

        return new GlobalResponseDto("Success signup", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        Account account = accountRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NotFoundUser)
        );

        // 비밀번호 맞는지 확인
        if(!passwordEncoder.matches(loginRequestDto.getAccountPw(), account.getAccountPw())) {
            throw new CustomException(ErrorCode.NotMatchPassword);
        }

        // 토큰 발급해주기
        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(loginRequestDto.getEmail());

        if(refreshToken.isPresent()) {// 로그아웃한 후 로그인을 다시하는건가
            RefreshToken refreshToken1 = refreshToken.get().updateToken(tokenDto.getRefreshToken());
            refreshTokenRepository.save(refreshToken1);

        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(newToken);
        }
        // 토큰 header에 넣어줘서 클라이언트에 전달하기
        setHeader(response, tokenDto);

        return new GlobalResponseDto("Success Login", HttpStatus.OK.value());
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    @Transactional
    public GlobalResponseDto check(EmailRequestDto emailRequestDto) {
        if (accountRepository.findByEmail(emailRequestDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.AlreadyHaveEmail);
        }
        return new GlobalResponseDto("아이디 중복검사 통과", HttpStatus.OK.value());
    }
}


