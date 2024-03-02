package com.example.demo.service;

import com.example.demo.repository.SpotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpotService {
    private final SpotJpaRepository spotJpaRepository;
}
