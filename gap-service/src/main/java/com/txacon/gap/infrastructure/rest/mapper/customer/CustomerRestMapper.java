package com.txacon.gap.infrastructure.rest.mapper.customer;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.rest.dto.customer.CustomerDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import com.txacon.gap.infrastructure.rest.mapper.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    uses = {AddressRestMapper.class, PhoneRestMapper.class, PasswordEncoderMapper.class},
    componentModel = "spring")
public interface CustomerRestMapper extends GenericRestMapper<CustomerDTO, Customer> {

  @Override
  @Mapping(source = "password", target = "password", qualifiedByName = "passwordEncoding")
  Customer toDomain(CustomerDTO customerDTO);

  @Override
  @Mapping(source = "password", target = "password", ignore = true)
  CustomerDTO toDTO(Customer entity);
}
