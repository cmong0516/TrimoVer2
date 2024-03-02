package com.example.demo.domain.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String nickName;
    private String password;
    private Integer age;
}
