package com.txacon.gap.infrastructure.db.jpa.bussines.repository;

import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BusinessCrudRepository extends JpaRepository<BusinessEntity, Long> {

    List<BusinessEntity> findByOwnId(Long id);

    List<BusinessEntity> findByOwn(CustomerEntity customerEntity);

    List<BusinessEntity> findByOwnAndActiveTrue(CustomerEntity customerEntity);
}
