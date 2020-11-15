package com.txacon.gap.infrastructure.rest.mapper;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.rest.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AddressRestMapper.class, PhoneRestMapper.class, PasswordEncoderMapper.class}, componentModel = "spring")
public interface CustomerRestMapper extends GenericRestMapper<CustomerDTO, Customer> {
    @Override
    @Mapping(source = "password", target = "passwordHash", qualifiedByName = "passwordEncoding")
    Customer toDomain(CustomerDTO customerDTO);

    @Override
    @Mapping(source = "passwordHash", target = "password", ignore = true)
    CustomerDTO toDTO(Customer entity);
}
