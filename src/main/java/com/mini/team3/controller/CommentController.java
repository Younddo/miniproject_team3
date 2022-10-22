package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.dto.request.CommentRequestDto;
import com.mini.team3.entity.CommentLike;
import com.mini.team3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity createComment(@PathVariable Long postId,
                                        @RequestBody CommentRequestDto commentRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto ,userDetails.getAccount());
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getAccount());
    }

    @GetMapping("/{postId}/comments/{commentId}/like")
    public ResponseEntity likeComment(@PathVariable Long postId,
                                      @PathVariable Long commentId,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails
                                      ) {
        return commentService.likeComment(postId, commentId, userDetails.getAccount());
    }

}
