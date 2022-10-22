package com.mini.team3.dto.response;

import com.mini.team3.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostUpdateDto {
    private LocalDateTime modifiedAt;
    public PostUpdateDto(Post post) {
        this.modifiedAt = post.getModifiedAt();
    }
}
