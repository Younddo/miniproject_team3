package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.GlobalResDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.dto.response.PostUpdateDto;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public GlobalResDto createPost(@RequestBody @Valid PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails==null){
            throw new CustomException(ErrorCode.NotFoundToken);
        }
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public PostUpdateDto updatePost(@PathVariable Long postId,
                                    @RequestBody @Valid PostRequestDto postRequestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, postRequestDto, userDetails.getAccount());
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public GlobalResDto deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails.getAccount());
    }
}
