package com.pichincha.backend.backendtest.services;

import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.dto.UserDto;
import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;
import com.pichincha.backend.backendtest.repository.StoreRepository;
import com.pichincha.backend.backendtest.services.impl.StoreServiceImpl;
import com.pichincha.backend.backendtest.util.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    private static final TestData testData = TestData.getInstance();

    @InjectMocks
    private StoreServiceImpl storeService;
    @Mock
    private StoreRepository storeRepository;

    @Test
    void shouldCreateANewStore(){
        //given
        UserDto owner = UserDto.builder()
                .id(1L)
                .build();
        StoreDto newStore = StoreDto.builder()
                .name("my awesome store")
                .category("groceries")
                .owner(owner)
                .build();
        StoreEntity savedStore = StoreEntity.builder()
                .id(1L)
                .name(newStore.getName())
                .category(newStore.getCategory())
                .owner(UserEntity.builder()
                        .id(1L)
                        .username("dani")
                        .createdDate(LocalDate.now())
                        .build())
                .build();
        when(storeRepository.save(any(StoreEntity.class)))
                .thenReturn(savedStore);
        //when
        StoreDto storeCreated = storeService.create(newStore);
        //then
        assertNotNull(storeCreated.getId());
        assertEquals(newStore.getCategory(), storeCreated.getCategory());
        assertEquals(newStore.getName(), storeCreated.getName());
        assertEquals(newStore.getOwner().getId(), storeCreated.getOwner().getId());

    }

    @Test
    void shouldReturnAStoreByName(){
        //given
        String nameOfStore = "my store";
        List<StoreEntity> storesInDB= testData.createListOfStoreEntitiesByName(nameOfStore);
        when(storeRepository.findByName(anyString()))
                .thenReturn(storesInDB);
        //when
        List<StoreDto> stores = storeService.findByName(nameOfStore);

        //then
        assertEquals(storesInDB.size(), stores.size());

    }

    @Test
    void shouldReturnAnUpdatedStore(){
        //given
        StoreDto storeToUpdate = testData.createStoreToUpdate();
        StoreEntity storeInDB = testData.createRandomStoreEntityWithGivenId(storeToUpdate.getId());
        when(storeRepository.save(any(StoreEntity.class)))
                .thenReturn(storeInDB);
        //when
        StoreDto updatedStore = storeService.update(storeToUpdate);
        //then
        Assertions.assertNotNull(updatedStore.getId());
        Assertions.assertNotNull( updatedStore.getName());
        Assertions.assertNotNull(updatedStore.getCategory());

    }
}
