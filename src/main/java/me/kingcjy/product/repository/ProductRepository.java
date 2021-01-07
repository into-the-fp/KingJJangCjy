package me.kingcjy.product.repository;

import me.kingcjy.product.domain.Product;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by KingCjy on 2021/01/07
 * Github: https://github.com/KingCjy
 */
public class ProductRepository {

    private HashMap<Long, Product> products = new HashMap<>();

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }
}
