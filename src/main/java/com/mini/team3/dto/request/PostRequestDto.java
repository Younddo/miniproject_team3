package com.mini.team3.dto.request;

import com.mini.team3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;
    private String tag;
}
