package com.example.Practice.domain.common;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    private Boolean isNewEventReception;
    private Boolean isReviewAnswerReception;
    private Boolean isEnquiryAnswerReception;

}
