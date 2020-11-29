package com.txacon.gap.infrastructure.rest.mapper.business;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.infrastructure.rest.dto.business.BusinessDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessRestMapper extends GenericRestMapper<BusinessDTO, Business> {
}
