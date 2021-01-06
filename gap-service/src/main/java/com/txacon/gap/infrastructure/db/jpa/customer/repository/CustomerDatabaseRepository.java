package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.port.RoleRepository;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomerDatabaseRepository implements CustomerRepository {

    private final CrudCustomerRepository crud;
    private final RoleRepository roleRepository;
    private final CustomerMapper mapper;

    @Override
    public Optional<Customer> findById(Long customerId) {
        return crud.findById(customerId).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return crud.findByEmail(email).map(mapper::toDomainForAuthentication);
    }

    @Override
    public Optional<Customer> findByEmailAndPassword(String email, String passwordHash) {
        return crud.findByEmailAndPassword(email, passwordHash).map(mapper::toDomainForAuthentication);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setRoles(customer.getRoles().stream().map(this::findRole)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
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


    private Optional<Role> findRole(Role e) {
        return roleRepository.findByName(e.getRole());
    }
}


