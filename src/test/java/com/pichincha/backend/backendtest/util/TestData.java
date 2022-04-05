package com.pichincha.backend.backendtest.util;

import com.github.javafaker.Faker;
import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.dto.UserDto;
import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class TestData {

    private static final TestData INSTANCE = new TestData();
    private static final Faker faker = new Faker();

    public static TestData getInstance() {
        return INSTANCE;
    }

    public List<StoreEntity> createListOfStoreEntitiesByName(String name) {
        return LongStream.range(1L, 5L)
                .mapToObj(id -> StoreEntity.builder()
                        .name(name)
                        .category("category")
                        .owner(createRandomUserEntity())
                        .products(Collections.emptyList())
                        .build())
                .collect(Collectors.toList());
    }

    public UserEntity createRandomUserEntity() {
        return UserEntity.builder()
                .id(faker.number().randomNumber())
                .username(faker.name().username())
                .createdDate(LocalDate.now())
                .build();
    }


    public StoreDto createNewStoreRequest() {
        return StoreDto.builder()
                .name(faker.harryPotter().character())
                .category(faker.commerce().material())
                .owner(UserDto.builder().id(1L).build())
                .build();
    }

    public StoreDto createMockStoreFromANewStore(StoreDto newStore) {
        return StoreDto.builder()
                .id(faker.number().randomNumber())
                .name(newStore.getName())
                .category(newStore.getCategory())
                .owner(createRandomUserDto())
                .build();

    }

    private UserDto createRandomUserDto() {
        return UserDto.builder()
                .id(faker.number().randomNumber())
                .username(faker.name().username())
                .createdDate(LocalDate.now())
                .build();


    }

    public StoreDto createStoreToUpdate() {
        return StoreDto.builder()
                .id(faker.number().randomNumber())
                .name(faker.funnyName().name())
                .category(faker.commerce().productName())
                .build();
    }

    public StoreEntity createRandomStoreEntityWithGivenId(Long id) {
        return StoreEntity.builder()
                .id(id)
                .name(faker.dragonBall().character())
                .category(faker.programmingLanguage().name())
                .owner(createRandomUserEntity())
                .products(Collections.emptyList())
                .build();
    }
}
