package com.mini.team3.customutil;



public class RandomSentence {
    private final static String[] arr = {
            "허리수술 4000만원! 당장 바르게 앉으세요^_^",
            "오늘도 힘찬 하루를 보내보아요!",
            "같이 일하고 싶은 개발자, 같이 밥먹고 싶은 개발자",
            "주 100시간을 밀도있게 집중하였나요??",
            "팀원들과 예쁜말로 소통하고 있나요??",
            "항해99 9기 C반 화이팅!",
            "팀원들과 사이좋게, 으쌰으쌰~",
            "프론트, 백 소통 잘 하고 계시죠?",
            "힘내자 힘!",
            "코딩에 흠뻑 빠져보아요"};

    public static String getSentence() {
        return arr[(int)(Math.random() * 10)];
    }
}