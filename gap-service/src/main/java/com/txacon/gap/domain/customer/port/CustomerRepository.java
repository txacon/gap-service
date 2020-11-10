package com.txacon.gap.domain.customer.port;

import com.txacon.gap.domain.customer.domain.Customer;

import java.util.Optional;
import java.util.stream.Stream;

public interface CustomerRepository {

    public Optional<Customer> findById(Long customerId);

    public Optional<Customer> findByEmail(String email);

    public Optional<Customer> findByEmailAndPasswordHash(String email, String passwordHash);

    Customer save(Customer customer);

    void delete(Customer customer);

    void deleteById(Long customerId);

    Stream<Customer> getCustomers();


}
