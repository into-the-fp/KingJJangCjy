package me.kingcjy.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.kingcjy.common.Money;
import me.kingcjy.common.event.Events;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
@ToString
@EqualsAndHashCode
public class Order {

    private Long id;
    private OrderCode orderCode;

    private long userId;

    private List<OrderItem> orderItems;

    private Money orderAmount;

    private OrderStatus status;

    private LocalDateTime orderedAt;

    public Order(OrderCode orderCode, long userId, List<OrderItem> orderItems) {
        this.orderCode = orderCode;
        this.userId = userId;
        this.orderItems = orderItems;
        this.status = OrderStatus.PAYMENT_WAITING;
        this.orderedAt = LocalDateTime.now();

        this.orderAmount = orderItems.stream()
                .map(OrderItem::calculateOrderAmount)
                .reduce(new Money(0), Money::plus);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void cancel() {
        verifyNotYetShipped();
        this.status = OrderStatus.CANCELED;
        Events.raise(new OrderCanceledEvent(orderCode));
    }

    private void verifyNotYetShipped() {
        if (!isNotYetShipped())
            throw new AlreadyShippedException();
    }

    public boolean isNotYetShipped() {
        return status == OrderStatus.PAYMENT_WAITING || status == OrderStatus.PREPARING;
    }
}
