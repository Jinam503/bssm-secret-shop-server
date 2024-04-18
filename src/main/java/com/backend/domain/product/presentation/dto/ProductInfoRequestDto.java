package com.backend.domain.product.presentation.dto;

public record ProductInfoRequestDto(
        Long productId,
        int quantity
) {
}
