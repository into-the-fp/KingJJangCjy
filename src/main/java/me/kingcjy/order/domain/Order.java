package me.kingcjy.order.domain;

import io.vavr.control.Option;
import lombok.Data;
import me.kingcjy.common.Money;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private final OrderCode orderCode;
    private final long userId;
    private final List<OrderItem> orderItems;
    private final Money orderAmount;
    private final OrderStatus status;
    private final LocalDateTime orderedAt;

    public Order(OrderCode orderCode, long userId, List<OrderItem> orderItems, Money orderAmount, OrderStatus status, LocalDateTime orderedAt) {
        this.orderCode = orderCode;
        this.userId = userId;
        this.orderItems = orderItems;
        this.orderAmount = orderAmount;
        this.status = status;
        this.orderedAt = orderedAt;
    }

    public static Money calculateAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::calculateOrderAmount)
                .reduce(new Money(0), Money::plus);
    }

    public static Order from(OrderCode orderCode, long userId, List<OrderItem> orderItems) {
        return new Order(orderCode, userId, orderItems, calculateAmount(orderItems), OrderStatus.PAYMENT_WAITING, LocalDateTime.now());
    }

    public Order setStatus(OrderStatus status) {
        return new Order(orderCode, userId, orderItems, orderAmount, status, orderedAt);
    }

    public Option<Order> cancel() {
        return status.isNotYetShipped() ? Option.none() : Option.of(setStatus(OrderStatus.CANCELED));
    }
}
