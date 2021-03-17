package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.PaymentMethodService;
import com.txacon.gap.domain.common.port.KeyEntityRepository;
import com.txacon.gap.domain.payment.entities.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl extends KeyAbstractService<PaymentType>
    implements PaymentMethodService {

  @Autowired
  protected PaymentMethodServiceImpl(KeyEntityRepository<PaymentType> repository) {
    super(repository);
  }
}
