package com.mini.team3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NotFoundPost(HttpStatus.NOT_FOUND.value(), "P001", "게시물을 찾을 수 없습니다."),
    NotMatchUser(HttpStatus.BAD_REQUEST.value(), "P002", "작성자가 일치하지 않습니다."),
    NotFoundComment(HttpStatus.NOT_FOUND.value(), "P003", "댓글이 존재하지 않습니다."),
    NotFoundCommentUser(HttpStatus.BAD_REQUEST.value(), "P004", "댓글 작성자가 아닙니다."),
    AlreadyHaveEmail(HttpStatus.BAD_REQUEST.value(), "P005", "이미 존재하는 아이디 입니다."),
    UnAuthorized(HttpStatus.UNAUTHORIZED.value(), "P006", "로그인을 해주세요."),
    NotMatchPassword(HttpStatus.BAD_REQUEST.value(), "P007", "비밀번호가 일치하지 않습니다."),
    NotFoundUser(HttpStatus.BAD_REQUEST.value(), "P008", "아이디가 존재하지 않습니다."),
    CantDelete(HttpStatus.BAD_REQUEST.value(), "P009", "삭제 권한이 없습니다."),
    NotFoundMypage(HttpStatus.BAD_REQUEST.value(), "P010", "마이페이지를 찾을 수 없습니다."),
    NotTeamLeader(HttpStatus.BAD_REQUEST.value(), "P011", "조장이 아니어서 공지를 쓸 수 없어요!");

    private final int httpStatus;
    private final String errorCode;
    private final String message;

}
