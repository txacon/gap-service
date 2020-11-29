package com.txacon.gap.infrastructure.db.jpa.bussines.mapper;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.payment.mapper.PaymentMethodMapper;
import com.txacon.gap.infrastructure.db.jpa.pricerange.mapper.PriceRangeMapper;
import com.txacon.gap.infrastructure.db.jpa.rating.mapper.AggregateRatingMapper;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {LocalTimeMapper.class, AggregateRatingMapper.class, PriceRangeMapper.class, PaymentMethodMapper.class})
public interface BusinessEntityMapper extends GenericDomainMapper<Business, BusinessEntity> {

    default Long mapToId(CustomerEntity customer) {
        return Objects.isNull(customer) ? null : customer.getId();
    }

    default CustomerEntity mapToCustomer(Long id) {
        if (id == null) return null;
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(id);
        return customerEntity;
    }

}
