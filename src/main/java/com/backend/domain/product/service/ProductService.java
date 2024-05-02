package com.backend.domain.product.service;

import com.backend.domain.product.presentation.dto.ProductModifyAmountRequestDto;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.domain.repository.ProductRepository;
import com.backend.domain.product.presentation.dto.ProductRequestDto;
import com.backend.domain.product.presentation.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()// 조건에 맞는 제품만 필터링
                .map(ProductResponseDto::new)
                .toList();
    }
    public List<ProductResponseDto> getAllProductsExceptLimited() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isLimited() || product.getStock() > 0) // 조건에 맞는 제품만 필터링
                .map(ProductResponseDto::new)
                .toList();
    }

    public void modifyProductAmountById(ProductModifyAmountRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.id())
                .orElseThrow(() -> new RuntimeException(""));
        product.updateStock(requestDto.stock());

        productRepository.save(product);
    }

    public void addProduct(ProductRequestDto product) {
        productRepository.save(product.toEntity());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
