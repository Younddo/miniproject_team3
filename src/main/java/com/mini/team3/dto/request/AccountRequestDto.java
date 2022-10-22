package com.mini.team3.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class AccountRequestDto {

    @NotBlank(message = "nickname은 공백일 수 없습니다.")
    @Size(min = 4, max = 12, message = "nickname은 4~12 개의 문자만 허용합니다.")
    @Pattern(regexp = "[a-z\\d]*${4,12}", message = "nickname은 소문자와 숫자의 조합만 허용합니다.")
    private String email;

    @NotBlank
    private String accountName;

    @NotBlank(message = "password는 공백일 수 없습니다.")
    @Size(min = 8, max = 16, message = "password는 8~!6 개의 문자만 허용합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$"
            , message = "password는 무조건 영문, 숫자, 특수문자를 각각 1글자 이상 포함해야 합니다.")
    private String accountPw;
    @NotBlank
    private String accountPwConfirm;

    @NotBlank
    private String accountTeam;

    @NotBlank
    private Boolean accountLeader;



    public void setEncodePwd(String encodePwd) {

        this.accountPw = encodePwd;
    }

}
