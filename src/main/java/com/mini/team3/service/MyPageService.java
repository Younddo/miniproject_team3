package com.mini.team3.service;

import com.mini.team3.dto.response.CommentResponseDto;
import com.mini.team3.dto.response.MyPageResponseDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.Post;
import com.mini.team3.entity.PostLike;
import com.mini.team3.repository.PostLikeRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public ResponseEntity showMyPage(Account account) {

        //내가 쓴 게시글 조회


        //내가 쓴 댓글 조회


        return new ResponseEntity(
                new MyPageResponseDto(account),
                HttpStatus.OK
        );
    }

    public ResponseEntity createOneSentence(Account account) {

    }
}
