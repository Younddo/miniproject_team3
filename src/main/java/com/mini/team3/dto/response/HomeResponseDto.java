package com.mini.team3.dto.response;

import com.mini.team3.customutil.Chrono;
import com.mini.team3.customutil.RandomSentence;

import java.time.LocalDateTime;

public class HomeResponseDto {
    private String goodWord;
    private long dDay;

    public HomeResponseDto() {
        this.goodWord = RandomSentence.getSentence();
        this.dDay = Chrono.dMinus(LocalDateTime.of(2022, 12,16,23,59));
    }
}
