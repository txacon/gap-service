package com.txacon.gap.infrastructure.db.jpa.rating.respository;

import com.txacon.gap.infrastructure.db.jpa.rating.entities.AggregateRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CrudRatingRepository extends JpaRepository<AggregateRatingEntity, String> {


}
