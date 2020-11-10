package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.domain.Phone;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PhoneMapper {

    Phone toDomain(PhoneEntity entity);

    PhoneEntity toEntity(Phone phone);
}
