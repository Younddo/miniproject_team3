package com.mini.team3.dto.response;

import com.mini.team3.entity.Account;
import com.mini.team3.entity.MyPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponseDto {

    private String accountName;

    private String accountTeam;

    private String oneSentence;

    private List<PostResponseDto> myPost;

    private List<CommentResponseDto> myComment;

    public MyPageResponseDto(Account account){

    }

    public MyPageResponseDto(Account account, List<PostResponseDto> postResponseDtos, List<CommentResponseDto> commentResponseDtos){
        this.accountName=account.getAccountName();
        this.accountTeam=account.getAccountTeam();
        this.oneSentence=account.getMyPage().getOneSentence();

        this.myPost=postResponseDtos;
        this.myComment=commentResponseDtos;
    }

    public MyPageResponseDto(MyPage myPage){

        this.oneSentence=myPage.getOneSentence();
    }
}
