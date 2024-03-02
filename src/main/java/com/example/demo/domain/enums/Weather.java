package com.example.demo.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Weather {
    SUNNY("맑음"),
    RAINY("우천"),
    CLOUDY("흐림"),
    SNOWY("눈");

    private final String name;

    Weather(String name) {
        this.name = name;
    }

    public static Weather getInstance(String name) {
        return Arrays.stream(Weather.values()).filter(weather -> weather.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
