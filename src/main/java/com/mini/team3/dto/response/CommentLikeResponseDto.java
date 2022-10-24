package com.mini.team3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikeResponseDto {

    private String data;

    private Integer likesCount;
}
