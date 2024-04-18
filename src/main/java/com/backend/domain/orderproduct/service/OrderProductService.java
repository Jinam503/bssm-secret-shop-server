package com.backend.domain.orderproduct.service;

import com.backend.domain.orderproduct.domain.OrderProduct;
import com.backend.domain.orderproduct.domain.repository.OrderProductRepository;
import com.backend.domain.orders.domain.Orders;
import com.backend.domain.orders.presentation.dto.OrderProductItemDto;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.presentation.dto.ProductInfoRequestDto;
import com.backend.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;

    public List<OrderProductItemDto> getOrderProductItemDtoList(Orders order) {
        return orderProductRepository.findByOrder(order).stream().map(
                OrderProductItemDto::new
        ).toList();
    }

    public void makeNewOrderProducts(Orders order, List<ProductInfoRequestDto> productInfos) {
        for (ProductInfoRequestDto productInfo : productInfos) {
            System.out.println(productInfo);
            System.out.println(order.toString());
            Product product = productService.findById(productInfo.productId());
            int newStock = product.getStock() - productInfo.quantity();
            product.updateStock(newStock);

            OrderProduct orderProduct = OrderProduct.builder()
                    .product(product)
                    .order(order)
                    .quantity(productInfo.quantity())
                    .build();

            orderProductRepository.save(orderProduct);

            System.out.println(orderProduct.getId());
        }
    }

    public void deleteOrderProductById(Orders order) {
        List<OrderProduct> orderProducts =  orderProductRepository.findByOrder(order);
        for (OrderProduct orderProduct : orderProducts) {
            int newStock = orderProduct.getProduct().getStock() + orderProduct.getQuantity();
            orderProduct.getProduct().updateStock(newStock);
            orderProductRepository.delete(orderProduct);
        }
    }
}
