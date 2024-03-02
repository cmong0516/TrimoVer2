package com.example.demo.domain.entity;

import com.example.demo.domain.dto.request.ReviewCreateRequest;
import com.example.demo.domain.dto.request.UpdateReviewRequest;
import com.example.demo.domain.dto.request.UpdateSpotRequest;
import com.example.demo.domain.enums.Companion;
import com.example.demo.domain.enums.PlaceType;
import com.example.demo.domain.enums.Weather;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    @JoinColumn(name = "spot_id")
    private Spot spot;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "users_id")
    private Users users;
    private LocalDateTime visitDateTime;
    @Enumerated(value = EnumType.STRING)
    private Weather weather;
    @Enumerated(value = EnumType.STRING)
    private Companion companion;
    @Enumerated(value = EnumType.STRING)
    private PlaceType placeType;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> images = new ArrayList<>();
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private Set<LikeReview> likeReviews = new HashSet<>();

    public Review(ReviewCreateRequest reviewCreateRequest, Users users, Spot spot) {

        this.title = reviewCreateRequest.getTitle();
        this.content = reviewCreateRequest.getContent();
        this.spot = spot;
        this.users = users;
        this.visitDateTime = stringToLocalDateTime(reviewCreateRequest.getVisitDateTime());
        this.weather = Weather.getInstance(reviewCreateRequest.getWeather());
        this.companion = Companion.getInstance(reviewCreateRequest.getCompanion());
        this.placeType = PlaceType.getInstance(reviewCreateRequest.getPlaceType());
    }

    private LocalDateTime stringToLocalDateTime(String visitDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(visitDateTime, formatter);
    }

    public void updateReview(UpdateReviewRequest updateReviewRequest, UpdateSpotRequest updateSpotRequest) {
        this.title = updateReviewRequest.getTitle();
        this.content = updateReviewRequest.getContent();
        this.spot = spot.updateSpot(updateSpotRequest);
        this.visitDateTime = stringToLocalDateTime(updateReviewRequest.getVisitDateTime());
        this.weather = Weather.getInstance(updateReviewRequest.getWeather());
        this.companion = Companion.getInstance(updateReviewRequest.getCompanion());
        this.placeType = PlaceType.getInstance(updateReviewRequest.getPlaceType());

    }

    public void addImage(ReviewPhoto reviewPhoto) {
        reviewPhoto.setReview(this);
        this.images.add(reviewPhoto);
    }

    public void removeImage(ReviewPhoto reviewPhoto) {
        this.images.remove(reviewPhoto);
        reviewPhoto.setReview(null);
    }

    public void removeAllImages() {
        images.forEach(reviewPhoto -> reviewPhoto.setReview(null));
        images.clear();
    }

    public void addLikeReview(LikeReview likeReview) {
        likeReview.setReview(this);
        this.likeReviews.add(likeReview);
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
