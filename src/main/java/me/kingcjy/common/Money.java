package me.kingcjy.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
@Getter
@ToString
@EqualsAndHashCode
public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money multiply(Integer multiple) {
        return new Money(value * multiple);
    }

    public boolean isGreaterThan(Money money) {
        return this.value > money.value;
    }

    public Money minus(Money minus) {
        return new Money(this.value - minus.value);
    }

    public Money plus(Money money) {
        return new Money(this.value + money.value);
    }
}
