package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.entities.Role;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericDomainMapper<Role, RoleEntity> {
}
