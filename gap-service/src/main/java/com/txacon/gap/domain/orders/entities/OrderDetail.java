package com.txacon.gap.domain.orders.entities;

import static lombok.AccessLevel.PRIVATE;

import com.txacon.gap.domain.products.entities.Product;
import java.math.BigDecimal;
import javax.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class OrderDetail {

  private Long id;
  private Product product;
  private Order order;
  private Integer quantity;
  private BigDecimal amount;
}
