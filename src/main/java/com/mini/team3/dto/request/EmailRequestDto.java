package com.mini.team3.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class EmailRequestDto {

    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 4, max = 12, message = "아이디는 4~12 개의 영문소문자와 숫자만 허용합니다.")
    @Pattern(regexp = "[a-z\\d]*${4,12}", message = "아이디는 소문자와 숫자의 조합만 허용합니다.")
    private String email;

}
