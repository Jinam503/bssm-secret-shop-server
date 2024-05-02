package com.backend.domain.product.presentation.dto;

import com.backend.domain.product.domain.Product;

public record ProductRequestDto(
        String name,
        String category,
        String description,
        String imageUrl,
        int price,
        int stock,
        boolean limited
) {
    public Product toEntity() {
        return Product.builder()
                .name(name)
                .category(category)
                .description(description)
                .imageUrl(imageUrl)
                .price(price)
                .stock(stock)
                .limited(limited)
                .build();
    }
}
