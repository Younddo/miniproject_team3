package com.mini.team3.repository;

import com.mini.team3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySort();

    //시간순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();
    //좋아요 정렬

    //조건
    List<Post> findPostsByTagAndAccount_AccountTeam(String tag, String team);
}
