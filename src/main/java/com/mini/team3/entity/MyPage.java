package com.mini.team3.entity;

import com.mini.team3.dto.request.MypageRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MyPageId;

    private String oneSentence;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    public MyPage(Account account){
        this.account=account;
    }

    public void createOneSentence(MypageRequestDto mypageRequestDto, Account account){
        this.oneSentence= mypageRequestDto.getOneSentence();
        this.account=account;
    }

    public void updateOneSentence(MypageRequestDto mypageRequestDto, Account account){
        this.oneSentence= mypageRequestDto.getOneSentence();
        this.account=account;
    }
}
