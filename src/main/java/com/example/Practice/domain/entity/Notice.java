package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Auditing.AuditingField;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notice extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    //새로운 이벤트 수신 여부
    private Boolean isNewEventReception;

    //리뷰 답변 알림 여부
    private Boolean isReviewAnswerReception;

    //문의 내역 답변 알림 여부
    private Boolean isEnquiryAnswerReception;

}
