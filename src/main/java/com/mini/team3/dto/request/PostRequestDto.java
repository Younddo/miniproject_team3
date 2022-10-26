package com.mini.team3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequestDto {
    private MultipartFile multipartFile;
    private String title;
    private String contents;
    private String tag;
}
