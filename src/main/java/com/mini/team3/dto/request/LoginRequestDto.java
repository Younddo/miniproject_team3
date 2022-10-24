package com.mini.team3.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String accountPw;


    public LoginRequestDto(String email, String accountPw) {
        this.email = email;
        this.accountPw = accountPw;
    }
}

