package com.mini.team3.S3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Uploader s3Uploader;

    @PostMapping("/test")
    public String test (@RequestPart(value = "img") MultipartFile multipartFile) {
        try {
            s3Uploader.uploadFiles(multipartFile, "testdir1");
        } catch (Exception e){
            return "실패";
        }

        return "성공";
    }


}
