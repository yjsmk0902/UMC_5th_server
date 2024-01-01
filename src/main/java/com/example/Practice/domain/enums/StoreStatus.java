package com.example.Practice.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreStatus {
    OPEN("영업 중"),
    CLOSE("영업 종료"),
    CLOSE_SOON("곧 영업 종료");

    private final String description;

    @JsonCreator
    public static StoreStatus from(String sub) {
        for (StoreStatus storeStatus : StoreStatus.values()) {
            if (storeStatus.getDescription().equals(sub)) {
                return storeStatus;
            }
        }
        throw new RuntimeException();       //TODO: Exception 구현 필요
    }
}
