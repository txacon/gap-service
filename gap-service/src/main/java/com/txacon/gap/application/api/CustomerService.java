package com.txacon.gap.application.api;

import com.txacon.gap.domain.customer.domain.Customer;

public interface CustomerService {

    Customer getById(Long customerId);

    Customer getByEmail(String email);

    Customer getByEmailAndPasswordHash(String email, String passwordHash);

    void deleteById(Long customerId);

    Customer update(Customer customer);

    Customer addCustomer(Customer customer);

}
