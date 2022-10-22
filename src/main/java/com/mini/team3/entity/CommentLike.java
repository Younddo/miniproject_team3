package com.mini.team3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentLikeId;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Comment comment;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Post post;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Account account;

    public CommentLike(Comment comment, Post post, Account account) {
        this.comment = comment;
        this.post = post;
        this.account = account;
    }
}
