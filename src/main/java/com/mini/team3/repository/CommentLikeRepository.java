package com.mini.team3.repository;

import com.mini.team3.entity.Comment;
import com.mini.team3.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentIdAndAccountId(Long commentId, Long accountId);
    void deleteByCommentIdAndAccountId(Long commentId, Long accountId);

    Optional<CommentLike> findCommentLikeByCommentIdAndAccountId(Long commentId, Long accountId);

    int countByCommentId(Long commentId);
}
