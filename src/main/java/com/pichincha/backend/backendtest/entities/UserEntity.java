package com.pichincha.backend.backendtest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String username;
    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToMany( fetch = FetchType.LAZY , mappedBy = "owner")
    private List<StoreEntity> stores;
}
