package me.kingcjy.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.kingcjy.common.Money;
import me.kingcjy.product.domain.Product;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
@ToString
@EqualsAndHashCode
public class OrderItem {
    private final long productId;
    private final Money price;
    private final int quantity;
    private final Money deliveryFee;

    public OrderItem(long productId, Money price, Money deliveryFee, int quantity) {
        this.productId = productId;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.quantity = quantity;
    }

    public static OrderItem from(Product product, int quantity) {
        return new OrderItem(product.getId(), product.getPrice(), product.getDeliveryFee(), quantity);
    }

    public Money calculateOrderAmount() {
        return price.multiply(quantity)
                .plus(deliveryFee);
    }
}
