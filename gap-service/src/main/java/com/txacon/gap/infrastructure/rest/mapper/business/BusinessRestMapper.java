package com.txacon.gap.infrastructure.rest.mapper.business;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.pricerange.entities.PriceRange;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import com.txacon.gap.infrastructure.rest.dto.business.BusinessDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import com.txacon.gap.infrastructure.rest.mapper.product.ProductRestMapper;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(
    componentModel = "spring",
    uses = {ProductRestMapper.class})
public interface BusinessRestMapper extends GenericRestMapper<BusinessDTO, Business> {

  Logger log = LoggerFactory.getLogger(BusinessRestMapper.class);

  default AggregateRating toAggregateRating(String value) {
    if (value == null) {
      return null;
    }
    try {
      return AggregateRating.valueOf(value);
    } catch (Exception e) {
      log.error("Invalid aggregate rating: {}", value);
      return null;
    }
  }

  default PriceRange toPriceRange(String value) {
    if (value == null) {
      return null;
    }
    try {
      return PriceRange.valueOf(value);
    } catch (Exception e) {
      log.error("Invalid aggregate rating: {}", value);
      return null;
    }
  }
}
