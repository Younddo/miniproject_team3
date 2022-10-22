package com.mini.team3.dto.response;

import com.mini.team3.entity.Comment;
import lombok.Getter;

@Getter
public class TestResponseDto {
    String data;

    public TestResponseDto(Comment comment) {
        this.data = comment.getComment();
    }

}
