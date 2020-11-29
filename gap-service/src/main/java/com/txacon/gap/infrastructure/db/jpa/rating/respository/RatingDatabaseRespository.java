package com.txacon.gap.infrastructure.db.jpa.rating.respository;

import com.txacon.gap.domain.rating.entities.AggregateRating;
import com.txacon.gap.domain.rating.port.RatingRepository;
import com.txacon.gap.infrastructure.db.jpa.rating.mapper.AggregateRatingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RatingDatabaseRespository implements RatingRepository {

    private final CrudRatingRepository respository;
    private final AggregateRatingMapper mapper;


    @Override
    public Optional<AggregateRating> findByName(AggregateRating aggregateRating) {
        return respository.findById(aggregateRating.name()).map(mapper::toDomain);
    }

    @Override
    public List<AggregateRating> findAll() {
        return respository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public AggregateRating save(AggregateRating aggregateRating) {
        return mapper.toDomain(respository.save(mapper.toEntity(aggregateRating)));
    }
}
