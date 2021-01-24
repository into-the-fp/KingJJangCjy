package me.kingcjy.order.domain;

import lombok.Data;

public interface OrderError {
    @Data
    class NoProduct implements OrderError {
        private final long productId;
    }
}
