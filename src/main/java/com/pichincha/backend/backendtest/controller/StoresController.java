package com.pichincha.backend.backendtest.controller;

import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.exception.StoreNotFoundException;
import com.pichincha.backend.backendtest.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreDto create(@RequestBody StoreDto newStore){
        return storeService.create(newStore);
    }

    @GetMapping
    public List<StoreDto> findByName(@RequestParam String name){
        return storeService.findByName(name);
    }

    @PutMapping
    public StoreDto update(@RequestBody StoreDto storeToUpdate){
        return storeService.update(storeToUpdate);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) throws StoreNotFoundException {
        return storeService.delete(id);
    }

}
