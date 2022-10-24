package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.dto.response.PostUpdateDto;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.s3.S3Uploader;
import com.mini.team3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public GlobalResponseDto createPost(@RequestPart(value = "img", required = false) MultipartFile multipartFile,
                                        @RequestPart(value = "post") @Valid PostRequestDto postRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        if (userDetails==null){
            throw new CustomException(ErrorCode.NotFoundToken);
        }
        return postService.createPost(multipartFile, postRequestDto, userDetails.getAccount());
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public PostUpdateDto updatePost(@PathVariable Long postId,
                                    @RequestPart(value = "img", required = false) MultipartFile multipartFile,
                                    @RequestPart(value = "post") @Valid PostRequestDto postRequestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.updatePost(postId, multipartFile, postRequestDto, userDetails.getAccount());
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public GlobalResponseDto deletePost(@PathVariable Long postId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails.getAccount());
    }

    // 전체게시글 조회(조건에 맞춰서)
    @GetMapping("/posts")
    public List<PostResponseDto> requestParam(@RequestParam(value = "sort", required = true, defaultValue ="최신순") String sort,
                                   @RequestParam(value = "accountTeam", required = true, defaultValue = "All") String accountTeam,
                                   @RequestParam(value = "tag", required = true, defaultValue = "All") String tag) {
        return postService.findAllPosts(sort, accountTeam, tag);
    }

    //우리반 게시글 조회(조건에 맞춰서)
    @GetMapping("/posts/myteam")
    public List<PostResponseDto> requestParam(@RequestParam(value = "sort", required = true, defaultValue ="최신순") String sort,
                                              @RequestParam(value = "tag", required = true, defaultValue = "All") String tag,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.findTeamPosts(sort, userDetails.getAccount().getAccountTeam(), tag);
    }

    // 게시글 상세조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto getOnePost(@PathVariable Long postId) {
        return postService.getOnePost(postId);
    }
}
