package com.backend.domain.product.presentation.dto;

import com.backend.domain.product.domain.Product;

public record ProductResponseDto(
        Long id,
        String name,
        String category,
        String description,
        String imageUrl,
        int price,
        int stock
) {
    public ProductResponseDto(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getImageUrl(),
                product.getPrice(),
                product.getStock()
        );
    }
}
