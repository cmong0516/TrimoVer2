package com.example.demo.repository;

import com.example.demo.domain.entity.Review;

public interface ReviewRepository {
    Review findReviewById(Long id);
}
