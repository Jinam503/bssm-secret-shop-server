package com.backend.domain.orders.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
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

    private LocalDateTime orderDate;

    public void accept() {
        accepted = true;
    }
}