package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.AggregateRatingService;
import com.txacon.gap.domain.common.port.KeyEntityRepository;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregateRatingServiceImpl extends KeyAbstractService<AggregateRating> implements AggregateRatingService {

    @Autowired
    public AggregateRatingServiceImpl(KeyEntityRepository<AggregateRating> repository) {
        super(repository);
    }
}
