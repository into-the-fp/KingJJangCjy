package me.kingcjy.order.domain;

import lombok.*;
import me.kingcjy.common.Money;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class OrderDto {

    @Getter
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class OrderRequest {
        private long orderId;
        private List<OrderItemRequest> orderItemRequests;
        private LocalDateTime orderedAt;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class OrderItemRequest {
        private long productId;
        private int quantity;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class OrderResponse {
        private long orderId;
        private int orderAmount;
        private List<OrderItemResponse> orderItemResponses;
        private LocalDateTime orderedAt;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class OrderItemResponse {
        private long orderItemId;
        private int productId;
        private int price;
        private int quantity;
        private int deliveryFee;
    }
}
