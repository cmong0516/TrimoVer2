package com.example.demo.domain.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserSignupRequest {
    private String nickName;
    private String password;
    private LocalDate birthDate;


    public UserSignupRequest(String nickName, String password, LocalDate birthDate) {
        this.nickName = nickName;
        this.password = password;
        this.birthDate = birthDate;
    }
}
