package com.mini.team3.service;


import com.mini.team3.dto.request.MypageRequestDto;
import com.mini.team3.dto.response.CommentResponseDto;
import com.mini.team3.dto.response.MyPageResponseDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.MyPage;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.repository.AccountRepository;
import com.mini.team3.repository.CommentRepository;
import com.mini.team3.repository.MypageRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final AccountRepository accountRepository;

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MypageRepository mypageRepository;

    @Transactional(readOnly = true)
    public ResponseEntity showMyPage(Account account) {

        //내가 쓴 게시글 조회
        List<Post> postList = postRepository.findPostsByAccount(account);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post foundPost : postList) {
            List<CommentResponseDto> test1 = new ArrayList<>();
            for (Comment comment : foundPost.getComments()) {
                test1.add(new CommentResponseDto(comment));
            }
            postResponseDtos.add(new PostResponseDto(foundPost, test1));
        }

        //내가 쓴 댓글 조회
        List<Comment> commentList = commentRepository.findCommentsByAccount(account);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for (Comment foundComment : commentList) {

            commentResponseDtos.add(new CommentResponseDto(foundComment));
        }

        Account account1 = accountRepository.findByEmail(account.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NotFoundCommentUser));

        return new ResponseEntity(
                new MyPageResponseDto(account1, postResponseDtos, commentResponseDtos),
                HttpStatus.OK
        );
    }

    @Transactional
    public ResponseEntity createOneSentence(@RequestBody MypageRequestDto mypageRequestDto, Account account) {

        MyPage myPage = mypageRepository.findById(account.getId()).orElseThrow(() ->
                new IllegalArgumentException("마이페이지를 찾을 수 없습니다.")
        );
        myPage.createOneSentence(mypageRequestDto, account);

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
