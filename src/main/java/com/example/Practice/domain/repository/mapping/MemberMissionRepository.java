package com.example.Practice.domain.repository.mapping;

import com.example.Practice.domain.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
