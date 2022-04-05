package com.pichincha.backend.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreDto {
    private Long id;
    private String name;
    private String category;
    private UserDto owner;
    private List<ProductDto> products;
}
