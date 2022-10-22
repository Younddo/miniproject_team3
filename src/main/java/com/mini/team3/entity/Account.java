package com.mini.team3.entity;

import com.mini.team3.dto.request.AccountRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String accountId;
    @NotBlank
    private String accountName;
    @NotBlank
    private String accountPw;
    @NotBlank
    private String accountPwConfirm;

    @NotBlank
    private String accountTeam;

    @NotBlank
    private Boolean accountLeader;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @OneToMany(mappedBy = "account")
    private List<Comment> comments;


    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="myPageId")
    private MyPage myPage;

    public Account(AccountRequestDto accountRequestDto) {
        this.accountId = accountRequestDto.getAccountId();
        this.accountName=accountRequestDto.getAccountName();
        this.accountPw = accountRequestDto.getAccountPw();
        this.accountPwConfirm = accountRequestDto.getAccountPwConfirm();
        this.accountTeam=accountRequestDto.getAccountTeam();
        this.accountLeader=accountRequestDto.getAccountLeader();
    }

}

