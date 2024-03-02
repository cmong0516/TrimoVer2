package com.example.demo.domain.entity;

import com.example.demo.domain.dto.request.SpotRequest;
import com.example.demo.domain.dto.request.UpdateSpotRequest;
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

    public Spot updateSpot(UpdateSpotRequest updateSpotRequest) {
        this.name = updateSpotRequest.getName();
        this.address = updateSpotRequest.getAddress();
        this.latitude = updateSpotRequest.getLatitude();
        this.longitude = updateSpotRequest.getLongitude();

        return this;
    }

}
