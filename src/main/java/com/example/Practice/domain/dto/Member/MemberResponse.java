package com.example.Practice.domain.dto.Member;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResponse {

    @Builder
    public record SignUpResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {
    }

}
