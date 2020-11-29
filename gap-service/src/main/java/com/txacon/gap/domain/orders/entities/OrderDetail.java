package com.txacon.gap.domain.orders.entities;

import com.txacon.gap.domain.products.entities.Product;

import javax.persistence.criteria.Order;
import java.math.BigDecimal;

public class OrderDetail {

    private Long id;
    private Product product;
    private Order order;
    private Integer quantity;
    private BigDecimal amount;
}
