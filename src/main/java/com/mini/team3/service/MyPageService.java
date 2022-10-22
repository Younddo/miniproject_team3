package com.mini.team3.service;

import com.mini.team3.dto.request.MypageRequestDto;
import com.mini.team3.dto.response.CommentResponseDto;
import com.mini.team3.dto.response.MyPageResponseDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.entity.*;
import com.mini.team3.repository.CommentRepository;
import com.mini.team3.repository.MypageRepository;
import com.mini.team3.repository.PostLikeRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final MypageRepository mypageRepository;

    @Transactional
    public ResponseEntity showMyPage(Account account) {

        //내가 쓴 게시글 조회
        List<PostResponseDto> postResponseDtos = new ArrayList<>();


        for (Post foundPost : account.getPosts()) {

            postResponseDtos.add(new PostResponseDto(foundPost));
        }

        //내가 쓴 댓글 조회
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();


        for (Comment foundComment : account.getComments()) {

            commentResponseDtos.add(new CommentResponseDto(foundComment));
        }

        return new ResponseEntity(
                new MyPageResponseDto(account, postResponseDtos, commentResponseDtos),
                HttpStatus.OK
        );
    }

    @Transactional
    public ResponseEntity createOneSentence(@RequestBody MypageRequestDto mypageRequestDto, Account account) {

        MyPage myPage = mypageRepository.findById(account.getId()).orElseThrow(() ->
                new IllegalArgumentException("마이페이지를 찾을 수 없습니다.")
        );
        myPage.createOneSentence(mypageRequestDto,account);

        mypageRepository.save(myPage);

        return new ResponseEntity(
                new MyPageResponseDto(myPage),
                HttpStatus.OK
        );

    }

    @Transactional
    public ResponseEntity updateOneSentence(MypageRequestDto mypageRequestDto, Account account) {
        MyPage myPage = mypageRepository.findById(account.getId()).orElseThrow(
                () -> new IllegalArgumentException("마이페이지를 찾을 수 없습니다.")
        );

        myPage.updateOneSentence(mypageRequestDto, account);

        return new ResponseEntity(
                new MyPageResponseDto(myPage),
                HttpStatus.OK
        );
    }
}
