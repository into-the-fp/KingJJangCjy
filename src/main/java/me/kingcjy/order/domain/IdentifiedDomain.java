package me.kingcjy.order.domain;

import lombok.Data;

@Data
public class IdentifiedDomain<T> {
    private final long id;
    private final T domain;

    public static IdentifiedDomain<Order> identifyOrder(long id, Order order) {
        return new IdentifiedDomain<>(id, order);
    }
}
