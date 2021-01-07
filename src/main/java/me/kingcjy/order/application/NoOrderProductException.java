package me.kingcjy.order.application;

import lombok.Getter;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
public class NoOrderProductException extends RuntimeException {

    private long productId;

    public NoOrderProductException(long productId) {
        this.productId = productId;
    }
}
