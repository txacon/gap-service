package com.txacon.gap.domain.orders.entities;

import com.txacon.gap.domain.customer.entities.Customer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Orders {

  private Long id;
  private Customer customer;
  private ShipmenAddress address;
  private LocalDateTime date;
  private List<OrderDetail> orderDetails;
}
