package com.backend.domain.orders.presentation.dto;

import com.backend.domain.orderproduct.domain.OrderProduct;

public record OrderProductItemDto(
        String name,
        int quantity
) {
    public OrderProductItemDto(OrderProduct o) {
        this(
                o.getProduct().getName(),
                o.getQuantity()
        );
    }
}
