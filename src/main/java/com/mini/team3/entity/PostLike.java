package com.mini.team3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "PostLikes")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postLikeId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="account_id")
    private Account account;

    public PostLike(Post post, Account account) {
        this.post = post;
        this.account = account;
    }

}
