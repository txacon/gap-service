package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import com.txacon.gap.domain.customer.domain.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.infrastructure.db.jpa.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

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
        return mapper.toDomain(crud.save(mapper.toEntity(customer)));
    }

    @Override
    public void delete(Customer customer) {
        crud.delete(mapper.toEntity(customer));

    }

    @Override
    public void deleteById(Long customerId) {
        crud.deleteById(customerId);
    }

    @Override
    public Stream<Customer> getCustomers() {
        return crud.getStreamAll().map(mapper::toDomain);
    }


}
