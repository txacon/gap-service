package com.txacon.gap.domain.customer.port;

import com.txacon.gap.domain.customer.entities.Customer;
import java.util.Optional;

public interface CustomerRepository {

  Optional<Customer> findById(Long customerId);

  Optional<Customer> findByEmail(String email);

  Optional<Customer> findByEmailAndPassword(String email, String passwordHash);

  Customer save(Customer customer);

  Customer update(Customer customer);

  void delete(Customer customer);

  void deleteById(Long customerId);
}
