package me.kingcjy.order.application;

import me.kingcjy.order.domain.OrderCode;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class RefundService {

    public void refund(OrderCode orderCode) {
        System.out.println("refund order[" + orderCode + "]");
    }
}
