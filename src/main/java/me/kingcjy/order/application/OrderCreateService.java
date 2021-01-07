package me.kingcjy.order.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.order.domain.Order;
import me.kingcjy.order.domain.OrderCode;
import me.kingcjy.order.domain.OrderDto;
import me.kingcjy.order.domain.OrderItem;
import me.kingcjy.order.repository.OrderRepository;
import me.kingcjy.product.domain.Product;
import me.kingcjy.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderCodeGenerator orderCodeGenerator;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderCode createOrder(long userId, OrderDto.OrderRequest orderRequest) {

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderDto.OrderItemRequest orderItemRequest : orderRequest.getOrderItemRequests()) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new NoOrderProductException(orderItemRequest.getProductId()));

            orderItems.add(new OrderItem(product.getId(), product.getPrice(), product.getDeliveryFee(), orderItemRequest.getQuantity()));
        }

        Order order = new Order(orderCodeGenerator.generate(), userId, orderItems);
        orderRepository.save(order);

        return order.getOrderCode();
    }
}
