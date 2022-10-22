package com.mini.team3.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    private String accountId;
    @NotBlank
    private String accountPw;

    public LoginRequestDto(String accountId, String accountPw) {
        this.accountId = accountId;
        this.accountPw = accountPw;
    }

}

