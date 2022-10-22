package com.mini.team3.repository;

import com.mini.team3.entity.Comment;
import com.mini.team3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByPost(Post post);
}
