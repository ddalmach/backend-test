package com.pichincha.backend.backendtest.services;

import com.pichincha.backend.backendtest.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto create(StoreDto newStore);
    List<StoreDto> findByName(String nameOfStore);
    StoreDto update(StoreDto storeToUpdate);
}
