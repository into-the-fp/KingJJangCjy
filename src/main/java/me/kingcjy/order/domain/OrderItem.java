package me.kingcjy.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.kingcjy.common.Money;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
@ToString
@EqualsAndHashCode
public class OrderItem {

    private Long id;
    private long productId;
    private Money price;
    private int quantity;
    private Money deliveryFee;

    public OrderItem(long productId, Money price, Money deliveryFee, int quantity) {
        this.productId = productId;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.quantity = quantity;
    }

    public Money calculateOrderAmount() {
        return price.multiply(quantity)
                .plus(deliveryFee);
    }
}
