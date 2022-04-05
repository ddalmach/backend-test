package com.pichincha.backend.backendtest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
@Getter
public class StoreEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private UserEntity owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "products_by_store",
        joinColumns = {@JoinColumn(name = "id_store")},
        inverseJoinColumns = {@JoinColumn(name = "id_product")}
    )
    private List<ProductEntity> products = new ArrayList<>();


}
