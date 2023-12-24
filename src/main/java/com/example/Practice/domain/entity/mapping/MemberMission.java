package com.example.Practice.domain.entity.mapping;

import com.example.Practice.domain.common.Auditing.AuditingTimeField;
import com.example.Practice.domain.enums.MissionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends AuditingTimeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MissionStatus missionStatus;
}
