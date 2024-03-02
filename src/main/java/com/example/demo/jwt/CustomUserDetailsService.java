package com.example.demo.jwt;


import com.example.demo.domain.entity.Users;
import com.example.demo.repository.UserJpaReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserJpaReposiroty userJpaReposiroty;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        Users users = userJpaReposiroty.findByNickName(nickName).orElseThrow(() -> new UsernameNotFoundException(nickName + " 유저를 찾을수 없습니다."));

        return new CustomUserDetails(users);
    }
}

