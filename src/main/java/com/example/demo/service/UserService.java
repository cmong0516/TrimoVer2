package com.example.demo.service;

import com.example.demo.domain.dto.request.LoginRequest;
import com.example.demo.domain.dto.request.UpdateUserRequest;
import com.example.demo.domain.dto.request.UserSignupRequest;
import com.example.demo.domain.dto.response.UserInfoResponse;
import com.example.demo.domain.entity.Users;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserJpaReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaReposiroty userJpaReposiroty;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public String create(UserSignupRequest userSignupRequest) {
        Users users = new Users(userSignupRequest);
        users.encodingPassword(passwordEncoder);
        userJpaReposiroty.save(users);

        return jwtTokenProvider.createToken(users.getNickName());
    }

    public UserInfoResponse myInfo(CustomUserDetails customUserDetails) {
        Users users = customUserDetails.getUsers();
        return new UserInfoResponse(users);
    }

    @Transactional
    public UserInfoResponse update(CustomUserDetails customUserDetails, UpdateUserRequest updateUserRequest) {
        Users users = customUserDetails.getUsers().updateUser(updateUserRequest, passwordEncoder);

        return new UserInfoResponse(users);
    }

    public boolean login(CustomUserDetails customUserDetails, LoginRequest loginRequest) {
        if (customUserDetails == null) {
            String nickName = loginRequest.getNickName();
            Users users = userJpaReposiroty.findByNickName(nickName)
                    .orElseThrow(() -> new IllegalArgumentException(nickName + "은 존재하지 않는 아이디입니다."));

            return passwordEncoder.matches(loginRequest.getPassword(), users.getPassword());
        } else {
            return true;
        }

    }
}
