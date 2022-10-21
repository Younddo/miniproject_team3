package com.mini.team3.repository;

import com.mini.team3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<Comment, Long> {
}
