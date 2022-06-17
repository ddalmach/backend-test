package com.pichincha.backend.backendtest.services.impl;

import com.pichincha.backend.backendtest.dto.ProductDto;
import com.pichincha.backend.backendtest.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

import java.util.List;
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ProductDto> importProductsToStore(Long storeId) {
        return List.of(new ProductDto());
    }
}
