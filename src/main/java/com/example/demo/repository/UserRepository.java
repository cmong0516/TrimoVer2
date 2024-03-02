package com.example.demo.repository;

import com.example.demo.domain.dto.response.UserInfoResponse;

public interface UserRepository {
    UserInfoResponse getUserInfoResponseById(Long id);
}
