package com.txacon.gap.infrastructure.db.jpa.payment.mapper;

import com.txacon.gap.domain.payment.entities.PaymentType;
import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class PaymentMethodMapper {

    public PaymentType toDomain(PaymentMethodEntity paymentMethodEntity) {
        if (paymentMethodEntity == null) return null;
        return PaymentType.valueOf(paymentMethodEntity.getPaymentType());
    }

    public PaymentMethodEntity toEntity(PaymentType paymentType) {
        if (paymentType == null) return null;
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setPaymentType(paymentType.name());
        return paymentMethodEntity;
    }
}
