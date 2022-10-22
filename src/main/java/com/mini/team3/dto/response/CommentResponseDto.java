package com.mini.team3.dto.response;

import com.mini.team3.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    Long postId;
    Long commentId;
    String comment;

    public CommentResponseDto(Long postId, Long commentId, String comment) {
        this.postId = postId;
        this.commentId = commentId;
        this.comment = comment;
    }

    public CommentResponseDto(Comment comment){
        this.comment=comment.getComment();

    }
}
