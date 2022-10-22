package com.mini.team3.controller;

import com.mini.team3.config.UserDetailsImpl;
import com.mini.team3.entity.Account;
import com.mini.team3.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ldap.PagedResultsControl;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("")
    public ResponseEntity showMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails){

        Account account = userDetails.getAccount();
        return myPageService.showMyPage(account);
    }

    @PostMapping("")
    public ResponseEntity createOneSentence(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Account account = userDetails.getAccount();
        return myPageService.createOneSentence(account);
    }

}
