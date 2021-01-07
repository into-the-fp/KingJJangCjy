package me.kingcjy.order.application;

import me.kingcjy.order.domain.OrderCode;

import java.util.UUID;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class OrderCodeGenerator {

    public OrderCode generate() {
        return new OrderCode(UUID.randomUUID().toString());
    }
}
