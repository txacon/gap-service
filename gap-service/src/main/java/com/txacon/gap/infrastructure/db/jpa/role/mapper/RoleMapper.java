package com.txacon.gap.infrastructure.db.jpa.role.mapper;

import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.role.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper extends GenericDomainMapper<Role, RoleEntity> {

    @Mapping(target = "role", source = "name")
    Role toDomain(RoleEntity roleEntity);

    @Mapping(target = "name", source = "role")
    RoleEntity toEntity(Role role);


    default RoleName toRoleName(String name) {
        return RoleName.valueOf(name);
    }


    default String toStrRoleName(RoleName roleName) {
        return roleName.name();
    }
}
