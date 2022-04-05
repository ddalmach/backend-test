package com.pichincha.backend.backendtest.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void shouldLoadProductsWithStockGreaterThanFiveToAGivenStore(){
        //given

        //when
        int numberOfLoadProducts = service.importProductsToStore(1L);
        //then
        Assertions.assertEquals(2, numberOfLoadProducts);

    }

}
