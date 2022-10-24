package com.mini.team3.dto.response;

import com.mini.team3.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;

    private String accountName;
    private String comment;

    public CommentResponseDto(Long postId, Long commentId, String comment) {
        this.postId = postId;
        this.commentId = commentId;
        this.comment = comment;
    }

    public CommentResponseDto(Comment comment){
        this.postId=comment.getPost().getPostId();
        this.commentId=comment.getCommentId();
        this.accountName=comment.getAccount().getAccountName();
        this.comment=comment.getComment();

    }
}
