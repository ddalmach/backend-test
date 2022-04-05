package com.pichincha.backend.backendtest.services.impl;

import com.pichincha.backend.backendtest.dto.ProductDto;
import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.dto.UserDto;
import com.pichincha.backend.backendtest.entities.ProductEntity;
import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;
import com.pichincha.backend.backendtest.repository.StoreRepository;
import com.pichincha.backend.backendtest.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository repository;

    @Override
    public StoreDto create(StoreDto newStore) {
        var storeToPersist = StoreEntity.builder()
                .name(newStore.getName())
                .category(newStore.getCategory())
                .owner(UserEntity.builder()
                        .id(newStore.getOwner().getId())
                        .build())
                .build();
        StoreEntity persistedStored = repository.save(storeToPersist);
        return StoreDto.builder()
                .id(persistedStored.getId())
                .name(persistedStored.getName())
                .category(persistedStored.getCategory())
                .owner(mapUserEntityToUserDto(persistedStored.getOwner()))
                .build();
    }

    private UserDto mapUserEntityToUserDto(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .createdDate(userEntity.getCreatedDate())
                .build();
    }

    @Override
    public List<StoreDto> findByName(String nameOfStore) {
        List<StoreEntity> foundStores = repository.findByName(nameOfStore);
        return foundStores.stream()
                .map( this::mapStoreEntityToStoreDto)
                .collect(Collectors.toList());
    }

    private StoreDto mapStoreEntityToStoreDto(StoreEntity storeEntity){
        return StoreDto.builder()
                .id(storeEntity.getId())
                .name(storeEntity.getName())
                .category(storeEntity.getCategory())
                .owner(mapUserEntityToUserDto(storeEntity.getOwner()))
                .products(getListOfProducts(storeEntity.getProducts()))
                .build();
    }

    private List<ProductDto> getListOfProducts(List<ProductEntity> products) {
        return products.stream()
                .map(this::mapProductEntityToProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto mapProductEntityToProductDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();
    }

    @Override
    public StoreDto update(StoreDto storeToUpdate) {
        StoreEntity changedStore = mapStoreDtoToStoreEntity(storeToUpdate);
        changedStore = repository.save(changedStore);
        return mapStoreEntityToStoreDto(changedStore);

    }

    private StoreEntity mapStoreDtoToStoreEntity(StoreDto storeToUpdate) {
        return StoreEntity.builder()
                .id(storeToUpdate.getId())
                .name(storeToUpdate.getName())
                .category(storeToUpdate.getCategory())
                .build();
    }
}
