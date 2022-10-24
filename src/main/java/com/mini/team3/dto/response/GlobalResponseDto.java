package com.mini.team3.dto.response;

import com.mini.team3.customutil.RandomSentence;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GlobalResponseDto {

    private String msg;
    private int statusCode;

    public GlobalResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }


}
