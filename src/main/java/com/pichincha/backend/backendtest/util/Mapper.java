package com.pichincha.backend.backendtest.util;

import com.pichincha.backend.backendtest.dto.ProductDto;
import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.dto.UserDto;
import com.pichincha.backend.backendtest.entities.ProductEntity;
import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    public static StoreDto mapStoreEntityToStoreDto(StoreEntity storeEntity){
        return StoreDto.builder()
                .id(storeEntity.getId())
                .name(storeEntity.getName())
                .category(storeEntity.getCategory())
                .owner(mapUserEntityToUserDto(storeEntity.getOwner()))
                .products(mapProductsToProductDto(storeEntity.getProducts()))
                .build();
    }

    public static List<ProductDto> mapProductsToProductDto(List<ProductEntity> products) {
        return products.stream()
                .map(Mapper::mapProductEntityToProductDto)
                .collect(Collectors.toList());
    }

    public static UserDto mapUserEntityToUserDto(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .createdDate(userEntity.getCreatedDate())
                .build();
    }

    public static ProductDto mapProductEntityToProductDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .build();
    }

    public static StoreEntity mapStoreDtoToStoreEntity(StoreDto storeToUpdate) {
        return StoreEntity.builder()
                .id(storeToUpdate.getId())
                .name(storeToUpdate.getName())
                .category(storeToUpdate.getCategory())
                .build();
    }
}
