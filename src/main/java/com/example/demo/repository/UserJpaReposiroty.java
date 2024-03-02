package com.example.demo.repository;

import com.example.demo.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaReposiroty extends JpaRepository<Users,Long> {
    Optional<Users> findByNickName(String nickName);
}
