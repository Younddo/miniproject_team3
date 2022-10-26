package com.mini.team3.controller;

import com.google.gson.GsonBuilder;
import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.dto.response.PostUpdateDto;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public GlobalResponseDto createPost(@RequestParam(value = "img", required = false) MultipartFile multipartFile,
                                        @RequestParam(value = "title") @Valid String title,
                                        @RequestParam(value = "contents") @Valid String contents,
                                        @RequestParam(value = "tag") @Valid String tag,
//                                        @ModelAttribute PostRequestDto postRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        if (userDetails == null) {
            throw new CustomException(ErrorCode.UnAuthorized);
        }

//        PostRequestDto postRequestDto = new PostRequestDto(multipartFile, title, contents, tag);
        PostRequestDto postRequestDto = new PostRequestDto(multipartFile, title, contents, tag);
//        PostRequestDto postRequestDto = new GsonBuilder().serializeNulls().create().fromJson(json, PostRequestDto.class);


        return postService.createPost(multipartFile, postRequestDto, userDetails.getAccount());
    }

    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public PostUpdateDto updatePost(@PathVariable Long postId,
                                    @RequestParam(value = "img", required = false) MultipartFile multipartFile,
                                    @RequestParam(value = "title") @Valid String title,
                                    @RequestParam(value = "contents") @Valid String contents,
                                    @RequestParam(value = "tag") @Valid String tag,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        PostRequestDto postRequestDto = new PostRequestDto(multipartFile, title, contents, tag);
//        PostRequestDto postRequestDto = new GsonBuilder().serializeNulls().create().fromJson(json, PostRequestDto.class);

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
    public List<PostResponseDto> requestParam(@RequestParam(value = "sort", required = true, defaultValue = "최신순") String sort,
                                              @RequestParam(value = "accountTeam", required = true, defaultValue = "All") String accountTeam,
                                              @RequestParam(value = "tag", required = true, defaultValue = "All") String tag) {
        return postService.findAllPosts(sort, accountTeam, tag);
    }

    // 우리조 게시글 조회(조건에 맞춰서)
    @GetMapping("/posts/myteam")
    public List<PostResponseDto> requestParam(@RequestParam(value = "sort", required = true, defaultValue = "최신순") String sort,
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
