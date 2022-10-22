package com.mini.team3.repository;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.Post;
import com.mini.team3.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Boolean existsByPostAndAccount(Post post, Account account);

    void deleteByPostAndAccount(Post post, Account account);
}
