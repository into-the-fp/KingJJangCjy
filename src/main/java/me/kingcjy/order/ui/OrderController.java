package me.kingcjy.order.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.common.event.Events;
import me.kingcjy.order.application.OrderCancelService;
import me.kingcjy.order.application.OrderCreateService;
import me.kingcjy.order.application.RefundService;
import me.kingcjy.order.domain.OrderCanceledEvent;
import me.kingcjy.order.domain.OrderCode;
import me.kingcjy.order.domain.OrderDto;
import me.kingcjy.order.domain.OrderError;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@RequiredArgsConstructor
public class OrderController {
    private final OrderCreateService orderCreateService;
    private final OrderCancelService orderCancelService;
    private final RefundService refundService;

    public void createOrder(OrderDto.OrderRequest orderRequest) {
        orderCreateService.createOrder(1l, orderRequest)
                .peek(order -> Events.handle((OrderCanceledEvent event) -> refundService.refund(event.getOrderCode())))
                .peek(orderCreateService::doAddProduct)
                .peekLeft(OrderController::performHandleError);
    }

    public void cancelOrder(OrderCode orderCode) {
        orderCancelService.cancel(orderCode)
                .peek(order -> Events.handle((OrderCanceledEvent event) -> refundService.refund(event.getOrderCode())))
                .peek(order -> Events.raise(new OrderCanceledEvent(orderCode)));
    }

    public static void performHandleError(OrderError error) {
        if (error instanceof OrderError.NoProduct) {
            final long id = ((OrderError.NoProduct) error).getProductId();
            // TODO: handle error
//            throw new NoOrderProductException(((OrderError.NoProduct) error).getProductId());
        }
    }
}
