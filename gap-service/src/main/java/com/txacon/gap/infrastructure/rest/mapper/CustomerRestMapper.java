package com.txacon.gap.infrastructure.rest.mapper;

import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.infrastructure.rest.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {AddressRestMapper.class, PhoneRestMapper.class}, componentModel = "spring")
public interface CustomerRestMapper extends GenericRestMapper<CustomerDTO, Customer> {
}
