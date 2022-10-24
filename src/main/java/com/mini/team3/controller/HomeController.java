package com.mini.team3.controller;

import com.mini.team3.dto.response.HomeResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/api/home")
    public HomeResponseDto home() {
        return new HomeResponseDto();
    }
}
