package com.txacon.gap.infrastructure.db.jpa.rating.mapper;

import com.txacon.gap.domain.rating.entities.AggregateRating;
import com.txacon.gap.infrastructure.db.jpa.rating.entities.AggregateRatingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AggregateRatingMapper {

    public AggregateRating toDomain(AggregateRatingEntity aggregateRatingEntity) {
        if (aggregateRatingEntity == null) return null;
        return AggregateRating.valueOf(aggregateRatingEntity.getId());
    }

    public AggregateRatingEntity toEntity(AggregateRating aggregateRating) {
        if (aggregateRating == null) return null;
        AggregateRatingEntity aggregateRatingEntity = new AggregateRatingEntity();
        aggregateRatingEntity.setId(aggregateRating.name());
        return aggregateRatingEntity;
    }
}
