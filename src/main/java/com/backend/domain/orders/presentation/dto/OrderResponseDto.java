package com.backend.domain.orders.presentation.dto;

import com.backend.domain.orders.domain.Orders;
import com.backend.domain.orders.domain.repository.OrderRepository;
import jakarta.persistence.criteria.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record OrderResponseDto(
        Long id,
        String ordererName,
        boolean needDelivery,
        String deliveryPlace,
        int totalPrice,
        boolean accepted,
        LocalDateTime orderDate,
        List<OrderProductItemDto> orderedProducts
) {
    public OrderResponseDto(Orders order, List<OrderProductItemDto> orderedProducts) {
        this(
                order.getId(),
                order.getOrdererName(),
                order.isNeedDelivery(),
                order.getDeliverPlace(),
                order.getTotalPrice(),
                order.isAccepted(),
                order.getOrderDate(),
                orderedProducts
        );
    }
}
