package com.txacon.gap.domain.bussines.port;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.customer.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findById(Long id);

    List<Business> findByOwn(Customer customer);

    List<Business> findByOwnAndActive(Customer customer);

    List<Business> findAll();

    Business save(Business business);
}
