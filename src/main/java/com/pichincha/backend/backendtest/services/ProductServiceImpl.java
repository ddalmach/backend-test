package com.pichincha.backend.backendtest.services;

import com.pichincha.backend.backendtest.services.impl.ProductService;

public class ProductServiceImpl implements ProductService {
    @Override
    public int importProductsToStore(Long storeId) {
        return 2;
    }
}
