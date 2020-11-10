package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import com.txacon.gap.domain.customer.domain.Customer;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CrudCustomerRepository extends CrudRepository<CustomerEntity,Long> {
    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByEmailAndPasswordHash(String email, String passwordHash);

    Stream<CustomerEntity> getStreamAll();
}
