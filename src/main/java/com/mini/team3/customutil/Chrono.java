package com.mini.team3.customutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Chrono {

    public static long dPlus(LocalDateTime dayBefore) {
        return ChronoUnit.DAYS.between(dayBefore, LocalDateTime.now());
    }

    public static long dMinus(LocalDateTime dayAfter) {
        return ChronoUnit.DAYS.between(dayAfter, LocalDateTime.now());
    }

    public static String timesAgo(LocalDateTime dayBefore) {
        long gap = ChronoUnit.HOURS.between(dayBefore, LocalDateTime.now());
        if (gap == 0){
            return "방금 전";
        }else if (gap < 24){
            return gap + "시간 전";
        }else if (gap < 24 * 10) {
            return gap / 24 + "일 전";
        } else {
            return dayBefore.format(DateTimeFormatter.ofPattern("MM월 dd일"));
        }
    }

    public static String customForm(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MM월 dd일"));
    }
}
