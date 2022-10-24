package com.mini.team3.repository;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    void deleteByCommentAndAccount(Comment comment, Account account);

    Optional<CommentLike> findByCommentAndAccount(Comment comment, Account account);
}
