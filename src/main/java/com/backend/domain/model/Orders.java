package com.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ordererName;

    @Column
    private boolean needDelivery;

    @Column
    private String deliverPlace;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private boolean accepted;

    @Column(nullable = false)
    private String orderedProducts;

}