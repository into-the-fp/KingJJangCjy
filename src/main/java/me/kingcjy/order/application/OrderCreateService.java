package me.kingcjy.order.application;

import io.vavr.collection.Map;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.val;
import me.kingcjy.order.domain.*;
import me.kingcjy.product.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderCreateService {
    private static final long randomSeed = ThreadLocalRandom.current().nextLong();
    private Map<Long, Product> productRepository;
    private Map<Long, IdentifiedDomain<Order>> orderRepository;
    private long seq = 0;

    public Either<OrderError, Order> createOrder(long userId, OrderDto.OrderRequest orderRequest) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderDto.OrderItemRequest orderItemRequest : orderRequest.getOrderItemRequests()) {
            val productEither = productRepository.get(orderItemRequest.getProductId())
                    .toEither(() -> new OrderError.NoProduct(orderItemRequest.getProductId()));
            if (productEither.isLeft()) {
                return Either.left(productEither.getLeft());
            } else {
                orderItems.add(OrderItem.from(productEither.get(), orderItemRequest.getQuantity()));
            }
        }
        Order order = Order.from(OrderCodeGenerator.generateOrderCode(randomSeed), userId, orderItems);
        return Either.right(order);
    }

    public void doAddProduct(Order order) {
        orderRepository = orderRepository.put(seq, IdentifiedDomain.identifyOrder(seq, order));
        seq++;
    }
}
