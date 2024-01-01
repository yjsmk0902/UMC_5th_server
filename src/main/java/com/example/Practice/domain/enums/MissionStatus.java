package com.example.Practice.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {
    ONGOING("진행중"),
    COMPLETE("진행 완료");

    private final String description;

    @JsonCreator
    public static MissionStatus from(String sub) {
        for (MissionStatus missionStatus : MissionStatus.values()) {
            if (missionStatus.getDescription().equals(sub)) {
                return missionStatus;
            }
        }
        throw new RuntimeException();   //TODO: Exception 구현 필요
    }
}
