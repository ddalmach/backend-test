package com.pichincha.backend.backendtest.services;

import com.pichincha.backend.backendtest.dto.ProductDto;
import com.pichincha.backend.backendtest.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith({MockitoExtension.class})
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void shouldReturnAListOfLoadedProductsByTheGivenStoreWhenImportingProducts(){
        //when
        List<ProductDto> loadedProductsInStore = service.importProductsToStore(1L);
        //then
        assertThat(loadedProductsInStore, instanceOf(List.class));
    }
    @Test
    void shouldReturnAListThatContainsLoadedProductsByTheGivenStore(){
        List<ProductDto> loadedProductsInStore = service.importProductsToStore(1L);
        Assertions.assertFalse(loadedProductsInStore.isEmpty());
    }

    @Test
    void shouldReturnAListOfLoadedProductsThatHasAStockGreaterThanFive(){

    }

}
