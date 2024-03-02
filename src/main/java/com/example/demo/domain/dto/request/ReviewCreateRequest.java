package com.example.demo.domain.dto.request;

import lombok.Data;

@Data
public class ReviewCreateRequest {
    private String title;
    private String content;
    private String weather;
    private String companion;
    private String placeType;
    private String visitDateTime;
}
