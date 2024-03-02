package com.example.demo.domain.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String nickName;
    private String password;
}
