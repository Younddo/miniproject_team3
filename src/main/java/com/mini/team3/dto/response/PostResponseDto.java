package com.mini.team3.dto.response;

import com.mini.team3.customutil.Chrono;
import com.mini.team3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String accountName;
    private String title;
    private String contents;
    private String tag;

    private String accountTeam;

    private Boolean accountLeader;
    private List<CommentResponseDto> comments;
    private int postLikeCount;
    private String createdAt;
    private String modifiedAt;
    private String img;


    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtos) {
        this.postId = post.getPostId();
        this.accountName = post.getAccount().getAccountName();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.tag = post.getTag();
        this.accountTeam=post.getAccount().getAccountTeam();
        this.accountLeader=post.getAccount().getAccountLeader();
        this.comments = commentResponseDtos;
        this.postLikeCount = post.getPostLikeCount();
        this.createdAt = Chrono.timesAgo(post.getCreatedAt());
        this.modifiedAt = Chrono.timesAgo(post.getModifiedAt());
        this.img = post.getImg();
    }
}
