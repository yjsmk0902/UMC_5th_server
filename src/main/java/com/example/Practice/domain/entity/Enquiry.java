package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Auditing.AuditingTimeField;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Enquiry extends AuditingTimeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //제목
    @Column(nullable = false, length = 30)
    private String title;

    //내용
    @Column(nullable = false, length = 1000)
    private String content;

    //첨부 이미지(최대 3장)
    @Column(length = 50)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
