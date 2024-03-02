package com.example.demo.service;

import com.example.demo.domain.dto.response.ReviewResponse;
import com.example.demo.domain.entity.LikeReview;
import com.example.demo.domain.entity.Review;
import com.example.demo.domain.entity.Users;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.repository.LikeReviewJpaRepository;
import com.example.demo.repository.LikeReviewRepository;
import com.example.demo.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeReviewService {
    private final LikeReviewRepository likeReviewRepository;
    private final ReviewJpaRepository reviewJpaRepository;
    private final LikeReviewJpaRepository likeReviewJpaRepository;

    @Transactional
    public void likeReview(CustomUserDetails customUserDetails, Long reviewId) {
        Users users = customUserDetails.getUsers();
        Review review = reviewJpaRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        LikeReview likeReview = new LikeReview(users, review);
        likeReviewJpaRepository.save(likeReview);
    }

    public List<ReviewResponse> likeReviewByUser(CustomUserDetails customUserDetails) {
        List<LikeReview> likeReviewByUserId = likeReviewRepository.findLikeReviewByUserId(customUserDetails.getUsers().getId());

        return likeReviewByUserId.stream()
                .map(ReviewResponse::new)
                .toList();

    }
}
