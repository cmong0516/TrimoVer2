package com.example.demo.controller;

import com.example.demo.domain.dto.request.ReviewCreateRequest;
import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.dto.request.UpdateReviewRequest;
import com.example.demo.domain.dto.request.UpdateSpotRequest;
import com.example.demo.domain.dto.response.CreateReviewResponse;
import com.example.demo.jwt.CustomUserDetails;
import com.example.demo.service.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ReviewContorller {
    private final ReviewService reviewService;


    @PostMapping(value = "/user/review/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long create(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @RequestPart ReviewCreateRequest reviewCreateRequest,
                       @RequestPart SpotRequest spotRequest,
                       @RequestPart List<MultipartFile> images) {
        return reviewService.create(customUserDetails, reviewCreateRequest, spotRequest, images);
    }

    @GetMapping("/review/{id}")
    public CreateReviewResponse getReviewById(@PathVariable Long id) {
        return reviewService.getReview(id);
    }

    @PostMapping(value = "/user/review/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void update(@PathVariable Long id,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @RequestPart(required = false) UpdateReviewRequest updateReviewRequest,
                       @RequestPart(required = false) UpdateSpotRequest updateSpotRequest,
                       @RequestParam(required = false) List<String> images,
                       @RequestPart(required = false) List<MultipartFile> newImages) {
        reviewService.updateReview(id,customUserDetails, updateReviewRequest, updateSpotRequest, images, newImages);
    }
}
