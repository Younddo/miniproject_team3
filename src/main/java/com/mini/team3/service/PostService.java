package com.mini.team3.service;

import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.GlobalResponseDto;
import com.mini.team3.dto.response.PostUpdateDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Post;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 작성
    public GlobalResponseDto createPost(PostRequestDto postRequestDto, Account account) {

        Post post = new Post(postRequestDto, account);
        postRepository.save(post);
        return new GlobalResponseDto("Success Post", HttpStatus.OK.value());
    }

    // 게시글 수정
    public PostUpdateDto updatePost(Long postId, PostRequestDto postRequestDto, Account currentAccount) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundPost)
        );
        if (post.getAccount().getAccountId().equals(currentAccount.getAccountId())) {
            post.update(postRequestDto);
            return new PostUpdateDto(post);
//                    ResponseEntity<>(
//                    PostResponseDto.builder()
//                            .modifiedAt(post.getModifiedAt())
//                            .build(),
//                    HttpStatus.OK
//                    post.getModifiedAt(), HttpStatus.OK
//            );
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }

    // 게시물 삭제
    public GlobalResponseDto deletePost(Long postId, Account currentAccount) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundPost)
        );
        if (post.getAccount().getAccountId().equals(currentAccount.getAccountId())) {
            postRepository.deleteById(postId);
            return new GlobalResponseDto("게시글 삭제가 완료되었습니다!", HttpStatus.OK.value());
        } else {
            throw new CustomException(ErrorCode.NotMatchUser);
        }
    }


}
