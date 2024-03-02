package com.example.demo.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PlaceType {
    FOODS("맛집"),
    SIGHT_SEEING("관광"),
    REST("휴양"),
    ATTRACTION("명소");

    private final String name;

    PlaceType(String name) {
        this.name = name;
    }

    public static PlaceType getInstance(String name) {
        return Arrays.stream(PlaceType.values())
                .filter(placeType -> placeType.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
