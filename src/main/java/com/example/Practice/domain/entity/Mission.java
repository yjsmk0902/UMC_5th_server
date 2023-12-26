package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Auditing.AuditingField;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //내용
    @Column(nullable = false, length = 50)
    private String content;

    //보상
    @Column(nullable = false)
    private Integer reword;

    //만료일자
    private LocalDateTime deadline;
}
