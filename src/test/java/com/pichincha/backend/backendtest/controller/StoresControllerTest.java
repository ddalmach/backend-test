package com.pichincha.backend.backendtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.backend.backendtest.dto.StoreDto;
import com.pichincha.backend.backendtest.services.StoreService;
import com.pichincha.backend.backendtest.util.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoresController.class)
@ExtendWith(SpringExtension.class)
class StoresControllerTest {

    private static final ObjectMapper serializer = new ObjectMapper();
    private static final TestData testData = TestData.getInstance();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreService storeService;

    @Test
    void shouldReturnStatusCreatedWhenAStoreIsCreated() throws Exception {
        //given
        StoreDto storeToCreate = testData.createNewStoreRequest();
        StoreDto createdStore = testData.createMockStoreFromANewStore(storeToCreate);
        RequestBuilder request = MockMvcRequestBuilders.post("/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(serializer.writeValueAsString(storeToCreate));
        Mockito.when(storeService.create(Mockito.any(StoreDto.class)))
                .thenReturn(createdStore);
        //when
        ResultActions response = mockMvc.perform(request);

        //then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdStore.getId()))
                .andExpect(jsonPath("$.name").value(createdStore.getName()))
                .andExpect(jsonPath("$.category").value(createdStore.getCategory()));

    }



}
