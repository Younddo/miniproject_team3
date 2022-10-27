package com.mini.team3.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentRequestDto {

    @NotBlank
    private String comments;
}
