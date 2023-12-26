package com.example.Practice.domain.repository.mapping;

import com.example.Practice.domain.entity.mapping.MemberCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
}
