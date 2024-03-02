package com.example.demo.domain.dto.response;

import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.entity.LikeReview;
import com.example.demo.domain.entity.Review;
import com.example.demo.domain.entity.ReviewPhoto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private UserInfoResponse userInfoResponse;
    private LocalDateTime visitDateTime;
    private String weather;
    private String companion;
    private String placeType;
    private SpotRequest spotRequest;
    private List<String> images;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.userInfoResponse = new UserInfoResponse(review.getUsers());
        this.visitDateTime = review.getVisitDateTime();
        this.weather = review.getWeather().getName();
        this.companion = review.getCompanion().getName();
        this.placeType = review.getPlaceType().getName();
        this.spotRequest = new SpotRequest(review.getSpot());
        this.images = review.getImages().stream().map(ReviewPhoto::getUrl).toList();
    }

    public ReviewResponse(LikeReview likeReview) {
        this.id = likeReview.getReview().getId();
        this.title = likeReview.getReview().getTitle();
        this.content = likeReview.getReview().getContent();
        this.userInfoResponse = new UserInfoResponse(likeReview.getUsers());
        this.visitDateTime = likeReview.getReview().getVisitDateTime();
        this.weather = likeReview.getReview().getWeather().getName();
        this.companion = likeReview.getReview().getCompanion().getName();
        this.placeType = likeReview.getReview().getPlaceType().getName();
        this.spotRequest = new SpotRequest(likeReview.getReview().getSpot());
        this.images = likeReview.getReview().getImages().stream().map(ReviewPhoto::getUrl).toList();
    }
}
