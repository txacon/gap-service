package com.txacon.gap.infrastructure.db.jpa.payment.mapper;

import com.txacon.gap.domain.payment.entities.PaymentType;
import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class PaymentMethodMapper {

  public PaymentType toDomain(PaymentMethodEntity paymentMethodEntity) {
    if (paymentMethodEntity == null) {
      return null;
    }
    return PaymentType.valueOf(paymentMethodEntity.getId());
  }

  public PaymentMethodEntity toEntity(PaymentType paymentType) {
    if (paymentType == null) {
      return null;
    }
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(paymentType.name());
    return paymentMethodEntity;
  }
}
