package com.txacon.gap.infrastructure.rest.mapper;

import com.txacon.gap.domain.customer.entities.Address;
import com.txacon.gap.infrastructure.rest.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressRestMapper extends GenericRestMapper<AddressDTO, Address> {
}
