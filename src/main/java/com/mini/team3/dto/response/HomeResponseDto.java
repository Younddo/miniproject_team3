package com.mini.team3.dto.response;

import com.mini.team3.customutil.Chrono;
import com.mini.team3.customutil.RandomSentence;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HomeResponseDto {
    private String goodWord;
    private long dDay;

    public HomeResponseDto() {
        this.goodWord = RandomSentence.getSentence();
        this.dDay = Chrono.dMinus(LocalDateTime.of(2022, 12,16,23,59));
    }
}
