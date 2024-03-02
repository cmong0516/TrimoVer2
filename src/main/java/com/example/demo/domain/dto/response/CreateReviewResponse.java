package com.example.demo.domain.dto.response;

import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.entity.Review;
import com.example.demo.domain.entity.ReviewPhoto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateReviewResponse {
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

    public CreateReviewResponse(Review review) {
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
}
