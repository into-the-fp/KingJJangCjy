package me.kingcjy.order.domain;

import lombok.Getter;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
public class OrderCanceledEvent {

    private OrderCode orderCode;

    public OrderCanceledEvent(OrderCode orderCode) {
        this.orderCode = orderCode;
    }
}
