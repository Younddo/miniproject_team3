package com.mini.team3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.team3.dto.request.PostRequestDto;
import com.mini.team3.entity.Comment;
import com.mini.team3.entity.Account;
import com.mini.team3.entity.TimeStamped;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String tag;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<PostLike> postLikes = new ArrayList<>();

    @Column(nullable = false)
    private int postLikeCount;

    public Post (PostRequestDto postRequestDto, Account account) {
        this.account = account;
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.tag = postRequestDto.getTag();
    }

    public void update (PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.tag = postRequestDto.getTag();
    }

    public void postLikeUpdate(int size) {
        this.postLikeCount = size;
    }


}
