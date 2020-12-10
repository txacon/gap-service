package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.role.mapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(uses = {PhoneMapper.class, AddressMapper.class, RoleMapper.class}, componentModel = "spring")
public interface CustomerMapper extends GenericDomainMapper<Customer, CustomerEntity> {

    @Mapping(target = "password", ignore = true)
    Customer toDomain(CustomerEntity customer);

    @Mapping(target = "phones", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "password", ignore = false)
    Customer toDomainForAuthentication(CustomerEntity customerEntity);

    @Mapping(target = "id", ignore = true)
    CustomerEntity toEntity(Customer customer);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "fistName", source = "fistName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "active", source = "active"),
            @Mapping(target = "addresses", source = "addresses"),
            @Mapping(target = "phones", source = "phones"),
            @Mapping(target = "password", source = "password")
    })
    void updateCustomer(Customer customer, @MappingTarget Customer customerToUpd);

}
