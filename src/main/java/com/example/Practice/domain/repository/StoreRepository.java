package com.example.Practice.domain.repository;

import com.example.Practice.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
