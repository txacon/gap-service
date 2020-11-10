package com.txacon.gap.infrastructure.rest.mapper;

import com.txacon.gap.domain.customer.entities.Phone;
import com.txacon.gap.infrastructure.rest.dto.PhoneDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneRestMapper extends GenericRestMapper<PhoneDTO, Phone> {
}
