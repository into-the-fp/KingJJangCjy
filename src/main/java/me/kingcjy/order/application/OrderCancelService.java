package me.kingcjy.order.application;

import io.vavr.collection.Map;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import me.kingcjy.order.domain.IdentifiedDomain;
import me.kingcjy.order.domain.Order;
import me.kingcjy.order.domain.OrderCode;
import me.kingcjy.order.repository.OrderRepository;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderCancelService {
    private final Map<Long, IdentifiedDomain<Order>> orderRepository;

    public Option<Order> cancel(OrderCode orderCode) {
        return OrderRepository.findByOrderCode(orderRepository, orderCode)
                .flatMap(Order::cancel);
    }
}
