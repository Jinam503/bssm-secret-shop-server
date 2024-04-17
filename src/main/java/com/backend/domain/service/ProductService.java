package com.backend.domain.service;

import com.backend.domain.model.Product;
import com.backend.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void modifyProductAmountById(Long id, Integer amount) {
        Product product = productRepository.findById(id).get();
        product.setStock(product.getStock() - amount);

    }

    public String getProductNameById(Long productId) {
        return productRepository.findById(productId).get().getName();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
