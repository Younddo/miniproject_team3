package com.mini.team3.dto.response;

import com.mini.team3.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private String accountName;
    private String comment;


    public CommentResponseDto(Comment comment){
        this.postId = comment.getPost().getPostId();
        this.comment = comment.getComment();
        this.accountName = comment.getAccount().getAccountName();
        this.commentId = comment.getCommentId();

    }
}
