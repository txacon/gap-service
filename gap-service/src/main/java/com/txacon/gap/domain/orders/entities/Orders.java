package com.txacon.gap.domain.orders.entities;

import static lombok.AccessLevel.PRIVATE;

import com.txacon.gap.domain.customer.entities.Customer;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Orders {

  private Long id;
  private Customer customer;
  private ShipmenAddress address;
  private LocalDateTime date;
  private List<OrderDetail> orderDetails;
}
