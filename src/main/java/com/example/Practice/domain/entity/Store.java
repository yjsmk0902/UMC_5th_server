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
@AllArgsConstructor
public class Store extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    private Address address;

    private Float reviewGrade;

}
