package com.backend.domain.product.presentation;

import com.backend.domain.product.presentation.dto.ProductModifyAmountRequestDto;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.presentation.dto.ProductRequestDto;
import com.backend.domain.product.presentation.dto.ProductResponseDto;
import com.backend.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add_product")
    public void addProduct(@RequestBody ProductRequestDto product) {
        productService.addProduct(product);
    }

    @PutMapping("/modify_product_amount")
    public void modifyProductAmount(@RequestBody ProductModifyAmountRequestDto requestDto) {
        productService.modifyProductAmountById(requestDto);
    }

    @DeleteMapping("/delete_product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
