package com.mini.team3.controller;

import com.mini.team3.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final S3Uploader s3Uploader;

    @PostMapping("/test")
    public String test (@RequestPart(value = "img") MultipartFile multipartFile) {
        try {
            return s3Uploader.uploadFiles(multipartFile, "testdir1");
        } catch (Exception e){
            return "실패";
        }
        
    }


}
