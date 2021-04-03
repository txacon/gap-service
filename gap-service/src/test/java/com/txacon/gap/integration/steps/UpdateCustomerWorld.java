package com.txacon.gap.integration.steps;

import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UpdateCustomerWorld {

  private CustomerEntity updateCustomer;

  public void reset() {
    updateCustomer = null;
  }
}
