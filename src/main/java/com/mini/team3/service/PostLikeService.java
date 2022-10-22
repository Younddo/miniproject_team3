package com.mini.team3.service;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.Post;
import com.mini.team3.entity.PostLike;
import com.mini.team3.exception.CustomException;
import com.mini.team3.exception.ErrorCode;
import com.mini.team3.repository.PostLikeRepository;
import com.mini.team3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public String createLike(Long postId, Account account) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundPost));
        int size = post.getPostLikes().size();
        PostLike postLike = new PostLike(post, account);
        if(!postLikeRepository.existsByPostAndAccount(post, account)){
            post.postLikeUpdate(size+1);
            postLikeRepository.save(postLike);
            return post.getPostId() +"번 게시물에 좋아요를 눌렀습니다." + postLike.getAccount().getAccountName() + "님";
        } else {
            postLikeRepository.deleteByPostAndAccount(post, account);
            post.postLikeUpdate(size-1);
            return post.getPostId() +"번 게시물에 좋아요를 취소했습니다." + postLike.getAccount().getAccountName() + "님";
        }

    }
}
