package me.kingcjy.product.domain;

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
public class Product {
    private Long id;
    private String name;
    private String description;

    private Money price;
    private Money deliveryFee;
    private int stock;

    public Product(String name, String description, Money price, Money deliveryFee, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.stock = stock;
    }

    public synchronized void subStock() {
        this.stock -= 1;
    }
}
