package me.kingcjy.order.domain;

import lombok.Getter;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
public class OrderCode {
    private String value;

    public OrderCode(String value) {
        this.value = value;
    }
}
