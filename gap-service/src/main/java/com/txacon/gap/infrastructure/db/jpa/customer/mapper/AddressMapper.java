package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.domain.Address;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address toDomain(AddressEntity addressEntity);

    AddressEntity toEntity(Address address);
}
