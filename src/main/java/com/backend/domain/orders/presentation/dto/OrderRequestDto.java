package com.backend.domain.orders.presentation.dto;

import com.backend.domain.product.presentation.dto.ProductInfoRequestDto;

import java.util.List;

public record OrderRequestDto(
        String ordererName,
        boolean needDelivery,
        String deliverPlace,
        int totalPrice,
        boolean accepted,
        String orderDate,
        List<ProductInfoRequestDto> productInfos
) {
}
