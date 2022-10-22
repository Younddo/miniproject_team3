package com.mini.team3.entity;

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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId;

    @Column(nullable = false)
    String comment;

    @JoinColumn(nullable = false)
    @ManyToOne
    Post post;

    @JoinColumn(nullable = false)
    @ManyToOne
    Account account;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    List<CommentLike> commentLikes = new ArrayList<>();

    public Comment(CommentRequestDto commentRequestDto, Post post, Account account) {
        this.comment = commentRequestDto.getComments();
        this.post = post;
        this.account = account;
    }
}
