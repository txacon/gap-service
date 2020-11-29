package com.txacon.gap.domain.payment.port;

import com.txacon.gap.domain.payment.entities.PaymentType;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepository {

    List<PaymentType> findAll();

    Optional<PaymentType> findBy(PaymentType paymentType);

    PaymentType save(PaymentType paymentType);
}
