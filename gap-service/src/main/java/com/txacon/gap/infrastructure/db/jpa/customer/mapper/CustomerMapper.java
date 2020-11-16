package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(uses = {PhoneMapper.class, AddressMapper.class, RoleMapper.class}, componentModel = "spring")
public interface CustomerMapper extends GenericDomainMapper<Customer, CustomerEntity> {

    @Mapping(target = "passwordHash", ignore = true)
    Customer toDomain(CustomerEntity customer);

    CustomerEntity toEntity(Customer customer);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "midName", source = "midName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "active", source = "active"),
            @Mapping(target = "addresses", source = "addresses"),
            @Mapping(target = "phones", source = "phones"),
            @Mapping(target = "passwordHash", source = "passwordHash")
    })
    void updateCustomer(Customer customer, @MappingTarget Customer customerToUpd);

}
