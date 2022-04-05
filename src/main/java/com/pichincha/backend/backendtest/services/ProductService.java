package com.pichincha.backend.backendtest.services;

import com.pichincha.backend.backendtest.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> importProductsToStore(Long storeId);
}
