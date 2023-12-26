package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Auditing.AuditingTimeField;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Area extends AuditingTimeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이름(..동)
    @Column(nullable = false, length = 10)
    private String name;
}
