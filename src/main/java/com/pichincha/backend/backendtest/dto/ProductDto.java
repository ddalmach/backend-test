package com.pichincha.backend.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private BigDecimal price;
    private long stock;
}
