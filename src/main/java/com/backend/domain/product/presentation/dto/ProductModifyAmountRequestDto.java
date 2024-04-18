package com.backend.domain.product.presentation.dto;

public record ProductModifyAmountRequestDto(
        Long id,
        Integer stock
) {
}
