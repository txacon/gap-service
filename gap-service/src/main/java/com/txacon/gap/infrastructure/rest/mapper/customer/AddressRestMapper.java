package com.txacon.gap.infrastructure.rest.mapper.customer;

import com.txacon.gap.domain.customer.entities.Address;
import com.txacon.gap.infrastructure.rest.dto.customer.AddressDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressRestMapper extends GenericRestMapper<AddressDTO, Address> {
}
