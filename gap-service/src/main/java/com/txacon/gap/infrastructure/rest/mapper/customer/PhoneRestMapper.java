package com.txacon.gap.infrastructure.rest.mapper.customer;

import com.txacon.gap.domain.customer.entities.Phone;
import com.txacon.gap.infrastructure.rest.dto.customer.PhoneDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneRestMapper extends GenericRestMapper<PhoneDTO, Phone> {

}
