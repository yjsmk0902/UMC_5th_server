package com.example.Practice.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(nullable = false, length = 10)
    private String city;

    @Column(nullable = false, length = 20)
    private String district;

    @Column(nullable = false, length = 10)
    private String zipCode;

}
