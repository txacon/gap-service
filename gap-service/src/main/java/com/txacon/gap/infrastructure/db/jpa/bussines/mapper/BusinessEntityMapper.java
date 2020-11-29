package com.txacon.gap.infrastructure.db.jpa.bussines.mapper;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.bussines.entities.PaymentType;
import com.txacon.gap.domain.bussines.entities.PriceRange;
import com.txacon.gap.domain.common.entities.AggregateRating;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.AggregateRatingEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.PaymentMethodEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.PriceRangeEntity;
import org.mapstruct.Mapper;

import java.time.*;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface BusinessEntityMapper extends GenericDomainMapper<Business, BusinessEntity> {

    default LocalTime toLocalTime(Date date) {
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        return LocalTime.of(zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond());
    }

    default Date toDate(LocalTime localTime) {
        Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    default AggregateRating toDomainAggregateRating(AggregateRatingEntity aggregateRatingEntity) {
        if (aggregateRatingEntity == null) return null;
        return AggregateRating.valueOf(aggregateRatingEntity.getAggregateRatingName());
    }

    default AggregateRatingEntity toEntityAggregationRating(AggregateRating aggregateRating) {
        if (aggregateRating == null) return null;
        AggregateRatingEntity aggregateRatingEntity = new AggregateRatingEntity();
        aggregateRatingEntity.setAggregateRatingName(aggregateRating.name());
        return aggregateRatingEntity;
    }

    default PriceRange toDomainPriceRangeRating(PriceRangeEntity priceRangeEntity) {
        if (priceRangeEntity == null) return null;
        return PriceRange.valueOf(priceRangeEntity.getPriceRangeName());
    }

    default PriceRangeEntity toEntityPriceRangeRating(PriceRange priceRange) {
        if (priceRange == null) return null;
        PriceRangeEntity priceRangeEntity = new PriceRangeEntity();
        priceRangeEntity.setPriceRangeName(priceRange.name());
        return priceRangeEntity;
    }

    default PaymentType toDomainPaymentType(PaymentMethodEntity paymentMethodEntity) {
        if (paymentMethodEntity == null) return null;
        return PaymentType.valueOf(paymentMethodEntity.getPaymentType());
    }

    default PaymentMethodEntity toEntityPaymentType(PaymentType paymentType) {
        if (paymentType == null) return null;
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setPaymentType(paymentType.name());
        return paymentMethodEntity;
    }
}
