package com.pichincha.backend.backendtest.services.impl;

import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;
import com.pichincha.backend.backendtest.exception.StoreNotFoundException;
import com.pichincha.backend.backendtest.repository.StoreRepository;
import com.pichincha.backend.backendtest.services.StoreService;
import com.pichincha.backend.backendtest.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                        .username(newStore.getOwner().getUsername())
                        .createdDate(newStore.getOwner().getCreatedDate())
                        .build())
                .build();
        StoreEntity persistedStored = repository.save(storeToPersist);
        return StoreDto.builder()
                .id(persistedStored.getId())
                .name(persistedStored.getName())
                .category(persistedStored.getCategory())
                .owner(Mapper.mapUserEntityToUserDto(persistedStored.getOwner()))
                .build();
    }

    @Override
    public List<StoreDto> findByName(String nameOfStore) {
        List<StoreEntity> foundStores = repository.findByName(nameOfStore);
        return foundStores.stream()
                .map(Mapper::mapStoreEntityToStoreDto)
                .collect(Collectors.toList());
    }

    @Override
    public StoreDto update(StoreDto storeToUpdate) {
        StoreEntity entityToUpdate = Mapper.mapStoreDtoToStoreEntity(storeToUpdate);
        entityToUpdate = repository.save(entityToUpdate);
        return Mapper.mapStoreEntityToStoreDto(entityToUpdate);
    }

    @Override
    public int delete(Long id) throws StoreNotFoundException {
        Optional<StoreEntity> storeFound = repository.findById(id);
        if(storeFound.isEmpty()){
            throw new StoreNotFoundException("Store with id:" + id + " does not exists" );
        }
        repository.deleteById(id);
        return 1;
    }
}
