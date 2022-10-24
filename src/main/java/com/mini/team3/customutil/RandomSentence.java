package com.mini.team3.customutil;



public class RandomSentence {
    private final static String[] arr = {
            "허리수술 4000만원! 당장 바르게 앉으세요^_^",
            "하윙",
            "같이 일하고 싶은 개발자, 같이 밥먹고 싶은 개발자",
            "주 100시간을 밀도있게 집중하였나요??",
            "팀원들과 예쁜말로 소통하고 있나요??"};

    public static String getSentence() {
        return arr[(int)(Math.random() * 5)];
    }
}