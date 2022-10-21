package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody @Valid PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails==null){
            throw new CustomException(ErrorCode.NotFoundToken);
        }
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }
}
