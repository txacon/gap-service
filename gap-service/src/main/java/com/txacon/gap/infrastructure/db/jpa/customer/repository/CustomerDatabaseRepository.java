package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.AddressEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.PhoneEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.RoleEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomerDatabaseRepository implements CustomerRepository {

    private final CrudCustomerRepository crud;
    private final CustomerMapper mapper;

    @Override
    public Optional<Customer> findById(Long customerId) {
        return crud.findById(customerId).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return crud.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmailAndPasswordHash(String email, String passwordHash) {
        return crud.findByEmailAndPasswordHash(email, passwordHash).map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = mapper.toEntity(customer);
        if (customerEntity == null) return null;
        return mapper.toDomain(crud.save(customerEntity));
    }



    @Override
    public void delete(Customer customer) {
        crud.delete(mapper.toEntity(customer));

    }

    @Override
    public void deleteById(Long customerId) {
        crud.deleteById(customerId);
    }


}
