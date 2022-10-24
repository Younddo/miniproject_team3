package com.mini.team3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.team3.dto.request.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(nullable = false)
    @JsonIgnore
    @ManyToOne
    private Post post;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentLike> commentLikes = new ArrayList<>();

    private int commentLikeSize;

    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
        this.comment = commentRequestDto.getComments();
        this.post = post;
        this.account = account;
    }

    public void updateSize(int commentLikeSize) {
        this.commentLikeSize = commentLikeSize;
    }
}
