package com.txacon.gap.application.api;

import com.txacon.gap.domain.customer.entities.Customer;

public interface CustomerService {

  Customer getById(Long customerId);

  Customer getByEmail(String email);

  Customer getByEmailAndPassword(String email, String passwordHash);

  void deleteById(Long customerId);

  Customer update(Customer customer);

  Customer addCustomer(Customer customer);
}
