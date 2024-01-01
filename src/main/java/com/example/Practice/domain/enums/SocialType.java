package com.example.Practice.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    NAVER("네이버"),
    GOOGLE("구글"),
    KAKAO("카카오");

    private final String description;

    @JsonCreator
    public static SocialType from(String sub) {
        for (SocialType socialType : SocialType.values()) {
            if (socialType.getDescription().equals(sub)) {
                return socialType;
            }
        }
        throw new RuntimeException();       //TODO: Exception 구현 필요
    }
}