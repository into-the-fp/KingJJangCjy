package me.kingcjy.order.repository;

import io.vavr.Tuple2;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import me.kingcjy.order.domain.IdentifiedDomain;
import me.kingcjy.order.domain.Order;
import me.kingcjy.order.domain.OrderCode;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class OrderRepository {

    public static Option<Order> findByOrderCode(Map<Long, IdentifiedDomain<Order>> orders, OrderCode code) {
        return orders.find(pair -> pair._2.getDomain().getOrderCode().equals(code))
                .map(Tuple2::_2)
                .map(IdentifiedDomain::getDomain);
    }
}
