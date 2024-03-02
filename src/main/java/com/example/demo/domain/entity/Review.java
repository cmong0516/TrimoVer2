package com.example.demo.domain.entity;

import com.example.demo.domain.dto.request.ReviewCreateRequest;
import com.example.demo.domain.enums.Companion;
import com.example.demo.domain.enums.PlaceType;
import com.example.demo.domain.enums.Weather;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Review extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Review(ReviewCreateRequest reviewCreateRequest,Users users, Spot spot) {
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

    public void addImage(ReviewPhoto reviewPhoto) {
        reviewPhoto.setReview(this);
        this.images.add(reviewPhoto);
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
