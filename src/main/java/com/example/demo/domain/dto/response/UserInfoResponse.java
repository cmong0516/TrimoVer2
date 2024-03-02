package com.example.demo.domain.dto.response;

import com.example.demo.domain.entity.Users;
import com.example.demo.domain.enums.Role;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class UserInfoResponse{
    private String nickName;
    private Integer age;
    private Role role;

    public UserInfoResponse(Users users) {
        this.nickName = users.getNickName();
        this.age = users.getAge();
        this.role = users.getRole();
    }

    @QueryProjection
    public UserInfoResponse(String nickName, Integer age, Role role) {
        this.nickName = nickName;
        this.age = age;
        this.role = role;
    }
}

