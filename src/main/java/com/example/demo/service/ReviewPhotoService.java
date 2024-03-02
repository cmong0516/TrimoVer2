package com.example.demo.service;

import com.example.demo.repository.ReviewPhotoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewPhotoService {
    private final ReviewPhotoJpaRepository reviewPhotoJpaRepository;
}
