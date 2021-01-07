package me.kingcjy.order.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.common.event.Events;
import me.kingcjy.order.domain.Order;
import me.kingcjy.order.domain.OrderCanceledEvent;
import me.kingcjy.order.domain.OrderCode;
import me.kingcjy.order.repository.OrderRepository;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderCancelService {
    private final OrderRepository orderRepository;
    private final RefundService refundService;

    public void cancel(long userId, OrderCode orderCode) {
        Events.handle((OrderCanceledEvent event) -> refundService.refund(event.getOrderCode()));

        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(NoOrderException::new);

        order.cancel();
    }
}
