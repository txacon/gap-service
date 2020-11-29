package com.txacon.gap.infrastructure.db.jpa.pricerange.repository;

import com.txacon.gap.infrastructure.db.jpa.pricerange.entities.PriceRangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CrudPriceRangeRepository extends JpaRepository<PriceRangeEntity, Long> {

    Optional<PriceRangeEntity> findByPriceRangeName(String name);

}
