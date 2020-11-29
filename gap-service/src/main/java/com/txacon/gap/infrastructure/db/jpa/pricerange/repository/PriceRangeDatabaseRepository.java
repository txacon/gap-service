package com.txacon.gap.infrastructure.db.jpa.pricerange.repository;

import com.txacon.gap.domain.pricerange.entities.PriceRange;
import com.txacon.gap.domain.pricerange.port.PriceRangeRepository;
import com.txacon.gap.infrastructure.db.jpa.pricerange.mapper.PriceRangeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PriceRangeDatabaseRepository implements PriceRangeRepository {

    private final CrudPriceRangeRepository repository;
    private final PriceRangeMapper mapper;

    @Override
    public List<PriceRange> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<PriceRange> findByName(PriceRange priceRange) {
        return repository.findById(priceRange.name()).map(mapper::toDomain);
    }

    @Override
    public PriceRange save(PriceRange priceRange) {
        return mapper.toDomain(repository.save(mapper.toEntity(priceRange)));
    }
}
