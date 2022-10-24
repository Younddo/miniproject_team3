package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.customutil.RandomSentence;
import com.mini.team3.dto.request.AccountRequestDto;
import com.mini.team3.dto.request.LoginRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.jwt.util.JwtUtil;
import com.mini.team3.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final JwtUtil jwtUtil;
    private final AccountService accountService;

    //회원가입
    @PostMapping("/signup")
    public GlobalResponseDto signup(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        return accountService.signup(accountRequestDto);
    }
    //로그인
    @PostMapping("/login")
    public GlobalResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return accountService.login(loginRequestDto, response);
    }

    //access token 재발급
    @GetMapping("/issue/token") //access token이 만료됐을 경우
    public GlobalResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getAccount().getEmail(), "Access"));

        return new GlobalResponseDto("Success IssuedToken", HttpStatus.OK.value());
    }

}