package com.example.demo.service;

import com.example.demo.domain.dto.request.ReviewCreateRequest;
import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.dto.response.CreateReviewResponse;
import com.example.demo.domain.entity.Review;
import com.example.demo.domain.entity.ReviewPhoto;
import com.example.demo.domain.entity.Spot;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.repository.ReviewJpaRepository;
import com.example.demo.repository.ReviewPhotoJpaRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.SpotJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewJpaRepository reviewJpaRepository;
    private final SpotJpaRepository spotJpaRepository;
    private final ReviewRepository reviewRepository;
    private final S3UploadService s3UploadService;
    private final ReviewPhotoJpaRepository reviewPhotoJpaRepository;

    @Transactional
    public Long create(CustomUserDetails customUserDetails, ReviewCreateRequest reviewCreateRequest, SpotRequest spotRequest, List<MultipartFile> images) {

        Spot spot = new Spot(spotRequest);
        Review review = new Review(reviewCreateRequest, customUserDetails.getUsers(), spot);

        images.stream()
                .map(image -> new ReviewPhoto(image, s3UploadService.uploadFile(image)))
                .forEach(review::addImage);

        reviewJpaRepository.save(review);
        spotJpaRepository.save(spot);

        return review.getId();
    }

    public CreateReviewResponse getReview(Long id) {
        Review reviewById = reviewRepository.getReviewById(id);
        return new CreateReviewResponse(reviewById);
    }
}
