package com.example.demo.controller;

import com.example.demo.domain.dto.request.LoginRequest;
import com.example.demo.domain.dto.request.UpdateUserRequest;
import com.example.demo.domain.dto.request.UserSignupRequest;
import com.example.demo.domain.dto.response.UserInfoResponse;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String create(@RequestBody UserSignupRequest userSignupRequest) {
        return userService.create(userSignupRequest);
    }

    @PostMapping("/login")
    public boolean login(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody(required = false) LoginRequest loginRequest) {
        return userService.login(customUserDetails, loginRequest);
    }

    @GetMapping("/user/myInfo")
    public UserInfoResponse myInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return userService.myInfo(customUserDetails);
    }

    @PostMapping("/user/update")
    public UserInfoResponse updateUser(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.update(customUserDetails,updateUserRequest);
    }
}
