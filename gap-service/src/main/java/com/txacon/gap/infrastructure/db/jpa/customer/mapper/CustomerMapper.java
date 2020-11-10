package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.domain.Customer;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(uses = {PhoneMapper.class,AddressMapper.class})
public interface CustomerMapper {

    Customer toDomain(CustomerEntity customer);

    CustomerEntity toEntity(Customer customer);


}
