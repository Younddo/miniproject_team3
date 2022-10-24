package com.mini.team3.dto.response;

import com.mini.team3.customutil.Chrono;
import com.mini.team3.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private String accountName;
    private String comment;
    private int commentLikes;
    private String createdAt;


    public CommentResponseDto(Comment comment){
        this.postId=comment.getPost().getPostId();
        this.commentId=comment.getCommentId();
        this.accountName=comment.getAccount().getAccountName();
        this.comment=comment.getComment();
        this.commentLikes = comment.getCommentLikeSize();
        this.createdAt = Chrono.timesAgo(comment.getCreatedAt());
    }
}
