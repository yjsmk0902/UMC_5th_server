package com.example.Practice.domain.entity;

import com.example.Practice.domain.common.Address;
import com.example.Practice.domain.common.Auditing.AuditingField;
import com.example.Practice.domain.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이름
    private String title;

    //영업 상태(영업 중, 닫음, 곧 닫음)
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    //주소
    @Embedded
    private Address address;

    //평점(최대 5점)
    private Float reviewGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

}