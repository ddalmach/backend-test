package com.pichincha.backend.backendtest.repository;

import com.pichincha.backend.backendtest.entities.StoreEntity;
import com.pichincha.backend.backendtest.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@TestPropertySource("classpath:test.application.properties")
@Sql({"classpath:schema.sql"})
public class StoreRepositoryIntegrationTest {

    @Autowired
    private StoreRepository repository;

    @Container
    private static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.2");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
    @Test
    void shouldInsertAStoreEntity(){
        //given
        var owner = UserEntity.builder()
                .username("testuser")
                .createdDate(LocalDate.now())
                .build();
        var storeToInsert = StoreEntity.builder()
                .name("test store")
                .category("my Category")
                .owner(owner)
                .build();

        //when
        StoreEntity insertedStore= repository.save(storeToInsert);

        assertThat(insertedStore).isNotNull();

    }

}
