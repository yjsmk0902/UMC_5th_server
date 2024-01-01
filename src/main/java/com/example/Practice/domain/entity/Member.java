package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Address;
import com.example.Practice.domain.common.Auditing.AuditingTimeField;
import com.example.Practice.domain.enums.Gender;
import com.example.Practice.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends AuditingTimeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(nullable = false, length = 20)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    //나이(최대200)
    @Column(nullable = false)
    private Integer age;

    //주소
    @Embedded
    private Address address;

    //상세 주소
    @Column(length = 20)
    private String addressDetailed;

    //포인트
    private Integer point;

    //로그인 타입
    @Column(nullable = false)
    private SocialType socialType;

    //미션 달성 갯수(최대 10)
    private Integer successMission;

    // JWT refreshToken
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
