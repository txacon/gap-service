package com.txacon.gap.infrastructure.db.jpa.customer.repository;

import static java.util.Collections.emptyList;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.port.RoleRepository;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.mapper.CustomerMapper;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
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
    log.info("Customer to save: {}", customer);
    customer.setRoles(
        Optional.ofNullable(customer.getRoles()).map(e -> e.stream()
            .map(this::findRole)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList()))
            .orElse(emptyList()));
    CustomerEntity customerEntity = mapper.toEntity(customer);
    if (customerEntity == null) {
      return null;
    }
    return mapper.toDomain(crud.save(customerEntity));
  }

  public Customer update(Customer customer) {
    Optional<CustomerEntity> customerEntity = crud.findById(customer.getId());
    if (customerEntity.isPresent()) {
      CustomerEntity toSaveEntity = customerEntity.get();
      mapper.updateCustomerEntity(customer, toSaveEntity);
      log.info("CustomerEntity to save: {}", toSaveEntity);
      return mapper.toDomain(crud.save(toSaveEntity));
    }
    return null;
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
    return roleRepository.findByName(e.getRoleName());
  }
}
