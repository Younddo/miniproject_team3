package com.mini.team3.dto.response;

import com.mini.team3.entity.Account;
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
public class MyPageResponseDto {

    private String accountName;

    private Long accountTeam;

    private String oneSentence;

    private List<PostResponseDto> myPost;

    private List<CommentResponseDto> myComments;

    public MyPageResponseDto(Account account){

    }
}
