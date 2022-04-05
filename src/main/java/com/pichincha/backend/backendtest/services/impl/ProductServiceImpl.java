package com.pichincha.backend.backendtest.services.impl;

import com.pichincha.backend.backendtest.dto.ProductDto;
import com.pichincha.backend.backendtest.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductDto> importProductsToStore(Long storeId) {
        return List.of(new ProductDto());
    }
}
