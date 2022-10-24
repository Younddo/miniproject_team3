package com.mini.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;
    private String tag;
}
