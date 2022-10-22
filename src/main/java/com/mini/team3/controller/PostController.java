package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.dto.response.PostUpdateDto;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public GlobalResponseDto createPost(@RequestBody @Valid PostRequestDto postRequestDto,
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
    public GlobalResponseDto deletePost(@PathVariable Long postId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails.getAccount());
    }

    @GetMapping("/posts/requestParam")
    public List<Post> requestParam(@RequestParam(value = "sort", required = true, defaultValue ="createdAt") String sort,
                                   @RequestParam(value = "accountTeam", required = true, defaultValue = "all") String accountTeam,
                                   @RequestParam(value = "tag", required = true, defaultValue = "all") String tag)
            throws IOException {
        log.classification("sort = {}", "accountTeam = {}", "tag = {}", sort, accountTeam, tag);
        return postService.findAllPosts(sort, accountTeam, tag);
    }
}
