package com.example.demo.service;

import com.example.demo.domain.dto.request.ReviewCreateRequest;
import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.dto.request.UpdateReviewRequest;
import com.example.demo.domain.dto.request.UpdateSpotRequest;
import com.example.demo.domain.dto.response.ReviewResponse;
import com.example.demo.domain.entity.*;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewJpaRepository reviewJpaRepository;
    private final SpotJpaRepository spotJpaRepository;
    private final ReviewRepository reviewRepository;
    private final S3UploadService s3UploadService;
    private final LikeReviewJpaRepository likeReviewJpaRepository;

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

    public ReviewResponse getReview(Long id) {
        Review reviewById = reviewRepository.findReviewById(id);
        return new ReviewResponse(reviewById);
    }

    @Transactional
    public void updateReview(Long id, CustomUserDetails customUserDetails, UpdateReviewRequest updateReviewRequest, UpdateSpotRequest updateSpotRequest, List<String> images, List<MultipartFile> newImages) {
        Review reviewById = reviewJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰 Id 입니다."));

        if (!Objects.equals(reviewById.getUsers().getNickName(), customUserDetails.getUsers().getNickName())) {
            throw new IllegalStateException("본인이 작성한 리뷰만 수정할수 있습니다.");
        }

        reviewById.updateReview(updateReviewRequest, updateSpotRequest);

        List<ReviewPhoto> reviewImages = reviewById.getImages();

        if (images == null) {
            reviewById.removeAllImages();
        } else {
            reviewImages.stream()
                    .filter(image -> !images.contains(image.getUrl()))
                    .forEach(reviewById::removeImage);
        }




        newImages.stream()
                .map(image -> new ReviewPhoto(image, s3UploadService.uploadFile(image)))
                .forEach(reviewById::addImage);
    }


}
