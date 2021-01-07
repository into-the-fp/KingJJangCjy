package me.kingcjy.order.domain;

import lombok.Getter;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
public enum OrderStatus {
    PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED
}
