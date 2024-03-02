package com.example.demo.repository;

import com.example.demo.domain.entity.ReviewPhoto;

import java.util.List;

public interface ReviewPhotoRepository {
    List<ReviewPhoto> findReviewPhotoByReviewIdInUrls(Long reviewId, List<String> urls);
}
