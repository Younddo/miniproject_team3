package com.mini.team3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NotFoundPost(HttpStatus.NOT_FOUND.value(), "P001", "게시물을 찾을 수 없습니다."),
    NotFoundUser(HttpStatus.BAD_REQUEST.value(), "P002", "작성자가 일치하지 않습니다."),
    NotFoundComment(HttpStatus.NOT_FOUND.value(), "P003", "댓글이 존재하지 않습니다."),
    NotFoundCommentUser(HttpStatus.BAD_REQUEST.value(), "P004", "댓글 작성자가 아닙니다."),
    AlreadyHaveEmail(HttpStatus.CONFLICT.value(), "P005", "이미 존재하는 이메일 입니다."),
    NotFoundToken(HttpStatus.BAD_REQUEST.value(), "P006", "로그인을 해주세요.");

    private final int httpStatus;
    private final String errorCode;
    private final String message;

}
