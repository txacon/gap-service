package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.PriceRangeService;
import com.txacon.gap.domain.common.port.KeyEntityRepository;
import com.txacon.gap.domain.pricerange.entities.PriceRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceRangeServiceImpl extends KeyAbstractService<PriceRange>
    implements PriceRangeService {

  @Autowired
  public PriceRangeServiceImpl(KeyEntityRepository<PriceRange> repository) {
    super(repository);
  }
}
