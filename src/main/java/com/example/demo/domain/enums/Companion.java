package com.example.demo.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Companion {
    FAMILY("가족"),
    FRIEND("친구"),
    LOVER("연인"),
    ALONE("혼자");

    private final String name;

    Companion(String name) {
        this.name = name;
    }

    public static Companion getInstance(String name) {
        return Arrays.stream(Companion.values())
                .filter(companion -> companion.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
