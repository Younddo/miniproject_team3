package com.mini.team3.service;

import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.dto.response.PostResponseDto;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.Post;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public PostResponseDto createPost(PostRequestDto postRequestDto, Account account) {

        Post post = new Post(postRequestDto, account);
        postRepository.save(post);
        return postResponseDto = new PostResponseDto(post);


    }
}
