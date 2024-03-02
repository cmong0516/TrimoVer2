package com.example.demo.domain.dto.request;

import lombok.Data;

@Data
public class UpdateSpotRequest {
    private String name;
    private String address;
    private String latitude;
    private String longitude;
}
