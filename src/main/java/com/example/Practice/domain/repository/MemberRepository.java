package com.example.Practice.domain.repository;

import com.example.Practice.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
