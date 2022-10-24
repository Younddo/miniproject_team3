package com.mini.team3.repository;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByAccount(Account account);
}
