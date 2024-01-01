package com.example.Practice.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    FEMALE("여자"),
    MALE("남자"),
    NONE("선택 안함");

    private final String description;

    @JsonCreator
    public static Gender from(String sub) {
        for (Gender gender : Gender.values()) {
            if (gender.getDescription().equals(sub)) {
                return gender;
            }
        }
        throw new RuntimeException();   //TODO: Exception 구현 필요
    }
}
