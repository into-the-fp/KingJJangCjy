package me.kingcjy.order.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.order.application.OrderCancelService;
import me.kingcjy.order.application.OrderCreateService;
import me.kingcjy.order.domain.OrderCode;
import me.kingcjy.order.domain.OrderDto;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderController {

    private final OrderCreateService orderCreateService;
    private final OrderCancelService orderCancelService;

    public void createOrder(OrderDto.OrderRequest orderRequest) {
        orderCreateService.createOrder(1l, orderRequest);
    }
    public void cancelOrder(OrderCode orderCode) {
        orderCancelService.cancel(1l, orderCode);
    }
}
