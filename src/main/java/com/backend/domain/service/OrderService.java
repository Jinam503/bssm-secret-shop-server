package com.backend.domain.service;

import com.backend.domain.dto.OrderInfoDto;
import com.backend.domain.model.Orders;
import com.backend.domain.model.Product;
import com.backend.domain.repository.OrderRepository;
import com.backend.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public List<OrderInfoDto> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        List<OrderInfoDto> orderInfoDtos = new ArrayList<>();

        for (Orders order : orders) {
            List<OrderInfoDto.OrderProduct> orderedProducts = new ArrayList<>();
            String[] pairs = order.getOrderedProducts().split(",");
            for (String pair : pairs) {
                String[] p = pair.split(":");

                Long productId = Long.parseLong(p[0]);
                Long quantity = Long.parseLong(p[1]);
                String productName = productService.getProductNameById(productId);

                orderedProducts.add(new OrderInfoDto.OrderProduct(productName, quantity));
            }

            orderInfoDtos.add(OrderInfoDto.builder()
                    .id(order.getId())
                    .ordererName(order.getOrdererName())
                    .needDelivery(order.isNeedDelivery())
                    .deliverPlace(order.getDeliverPlace())
                    .totalPrice(order.getTotalPrice())
                    .accepted(order.isAccepted())
                    .orderedProducts(orderedProducts.toArray(new OrderInfoDto.OrderProduct[0]))
                    .build());
        }

        return orderInfoDtos;
    }
    public Orders addOrder(Orders order) {
        String[] orderedProducts = order.getOrderedProducts().split(",");
        for (String orderedProduct : orderedProducts) {
            String[] productInfo = orderedProduct.split(":");
            if (productInfo.length != 2) {
                throw new RuntimeException("주문 정보의 형식이 올바르지 않습니다: " + orderedProduct);
            }
            long productId = Long.parseLong(productInfo[0]);
            int amount = Integer.parseInt(productInfo[1]);

            productService.modifyProductAmountById(productId, amount);
        }
        return orderRepository.save(order);
    }


    public void deleteOrderById(Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        String[] orderedProducts = order.getOrderedProducts().split(",");
        for (String orderedProduct : orderedProducts) {
            String[] productInfo = orderedProduct.split(":");
            if (productInfo.length != 2) {
                throw new RuntimeException("주문 정보의 형식이 올바르지 않습니다: " + orderedProduct);
            }
            long productId = Long.parseLong(productInfo[0]);
            int amount = Integer.parseInt(productInfo[1]);

            productService.modifyProductAmountById(productId, -amount);
        }
        orderRepository.deleteById(id);
    }

    public void updateAcceptedById(Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        order.setAccepted(true);
        orderRepository.save(order);
    }
}
