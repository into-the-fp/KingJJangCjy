package me.kingcjy.order.repository;

import me.kingcjy.order.domain.Order;
import me.kingcjy.order.domain.OrderCode;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class OrderRepository {

    private long seq = 0;
    private HashMap<Long, Order> orders = new HashMap<>();

    public Order save(Order order) {
        order.setId(++seq);
        return orders.put(order.getId(), order);
    }

    public Optional<Order> findById(long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Optional<Order> findByOrderCode(OrderCode orderCode) {
        return this.orders.values().stream()
                .filter(order -> orderCode.equals(order.getOrderCode()))
                .findFirst();
    }
}
