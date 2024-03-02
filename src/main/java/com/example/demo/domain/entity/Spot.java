package com.example.demo.domain.entity;

import com.example.demo.domain.dto.request.SpotRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Spot(SpotRequest spotRequest) {
        this.name = spotRequest.getName();
        this.address = spotRequest.getAddress();
        this.latitude = spotRequest.getLatitude();
        this.longitude = spotRequest.getLongitude();
    }

    public void addReview(Review review) {
        review.setSpot(this);
        reviews.add(review);
    }
}
