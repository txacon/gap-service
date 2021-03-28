package com.txacon.gap.infrastructure.db.jpa.pricerange.mapper;

import com.txacon.gap.domain.pricerange.entities.PriceRange;
import com.txacon.gap.infrastructure.db.jpa.pricerange.entities.PriceRangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class PriceRangeMapper {

  public PriceRange toDomain(PriceRangeEntity priceRangeEntity) {
    if (priceRangeEntity == null) {
      return null;
    }
    return PriceRange.valueOf(priceRangeEntity.getId());
  }

  public PriceRangeEntity toEntity(PriceRange priceRange) {
    if (priceRange == null) {
      return null;
    }
    PriceRangeEntity priceRangeEntity = new PriceRangeEntity();
    priceRangeEntity.setId(priceRange.name());
    return priceRangeEntity;
  }
}
