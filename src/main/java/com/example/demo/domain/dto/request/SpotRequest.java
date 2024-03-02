package com.example.demo.domain.dto.request;

import com.example.demo.domain.entity.Spot;
import lombok.Data;

@Data
public class SpotRequest {
    private String name;
    private String address;
    private String latitude;
    private String longitude;

    public SpotRequest(String name, String address, String latitude, String longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public SpotRequest(Spot spot) {
        this.name = spot.getName();
        this.address = spot.getAddress();
        this.latitude = spot.getLatitude();
        this.longitude = spot.getLongitude();
    }
}
