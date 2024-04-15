package com.backend.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Dictionary;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class OrderInfoDto {
    private Long id;
    private String ordererName;
    private boolean needDelivery;
    private String deliverPlace;
    private int totalPrice;
    private boolean accepted;
    private OrderProduct[] orderedProducts;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderProduct {
        private String name;
        private Long amount;
    }
}
