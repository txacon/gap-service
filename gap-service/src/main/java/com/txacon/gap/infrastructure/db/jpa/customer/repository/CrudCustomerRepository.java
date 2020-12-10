package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CrudCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByEmailAndPassword(String email, String passwordHash);

}
