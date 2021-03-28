package com.txacon.gap.domain.orders.entities;

import com.txacon.gap.domain.products.entities.Product;
import java.math.BigDecimal;
import javax.persistence.criteria.Order;
import lombok.Data;

@Data
public class OrderDetail {

  private Long id;
  private Product product;
  private Order order;
  private Integer quantity;
  private BigDecimal amount;
}
