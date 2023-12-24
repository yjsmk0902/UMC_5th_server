package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Address;
import com.example.Practice.domain.common.Auditing.AuditingTimeField;
import com.example.Practice.domain.enums.Category;
import com.example.Practice.domain.enums.Gender;
import com.example.Practice.domain.enums.SocialType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends AuditingTimeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @Embedded
    private Address address;

    private String addressDetailed;

    private Integer point;

    private SocialType socialType;

    private Set<Category> favoriteCategoryList;

}
