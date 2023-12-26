package com.example.Practice.domain.repository;

import com.example.Practice.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
