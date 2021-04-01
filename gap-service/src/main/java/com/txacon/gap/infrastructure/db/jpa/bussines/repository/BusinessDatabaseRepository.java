package com.txacon.gap.infrastructure.db.jpa.bussines.repository;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.bussines.port.BusinessRepository;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.mapper.BusinessEntityMapper;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.mapper.CustomerMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BusinessDatabaseRepository implements BusinessRepository {

  private final BusinessCrudRepository repository;
  private final BusinessEntityMapper mapper;
  private final CustomerMapper customerMapper;

  @Override
  public Optional<Business> findById(Long id) {
    return repository.findById(id).map(mapper::toDomain);
  }

  @Override
  public List<Business> findByOwn(Customer customer) {
    CustomerEntity customerEntity = customerMapper.toEntity(customer);
    return repository.findByOwn(customerEntity).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Business> findByOwnId(Long customerId) {
    List<BusinessEntity> findByOwnId = repository.findByOwnId(customerId);
    return findByOwnId.stream().map(mapper::toDomain).collect(Collectors.toList());
  }

  @Override
  public List<Business> findByOwnAndActive(Customer customer) {
    CustomerEntity customerEntity = customerMapper.toEntity(customer);
    return repository.findByOwnAndActiveTrue(customerEntity).stream()
        .map(mapper::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<Business> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
  }

  @Override
  public Business save(Business business) {
    return mapper.toDomain(repository.save(mapper.toEntity(business)));
  }

  @Override
  public Business update(Business business) {
    Optional<BusinessEntity> toSaveOptional = repository.findById(business.getId());
    if (toSaveOptional.isPresent()) {
      BusinessEntity e = toSaveOptional.get();
      mapper.updateBussines(business, e);
      return mapper.toDomain(repository.save(e));
    }
    return null;
  }

  @Override
  public void deleteById(Long bussinesId) {
    if (Objects.nonNull(bussinesId)) {
      repository.deleteById(bussinesId);
    }
  }
}
