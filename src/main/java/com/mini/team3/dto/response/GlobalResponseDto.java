package com.mini.team3.dto.response;

import com.mini.team3.customutil.RandomSentence;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GlobalResponseDto {

    private String message;
    private int statusCode;


    public GlobalResponseDto(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


}
