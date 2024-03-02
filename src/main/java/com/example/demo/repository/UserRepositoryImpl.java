package com.example.demo.repository;

import com.example.demo.domain.dto.response.QUserInfoResponse;
import com.example.demo.domain.dto.response.UserInfoResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.demo.domain.entity.QUsers.users;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public UserInfoResponse getUserInfoResponseById(Long id) {
        return jpaQueryFactory.select(new QUserInfoResponse(users.nickName, users.age, users.role)).from(users).fetchOne();
    }
}
