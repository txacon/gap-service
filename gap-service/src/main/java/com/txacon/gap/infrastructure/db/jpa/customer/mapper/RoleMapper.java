package com.txacon.gap.infrastructure.db.jpa.customer.mapper;

import com.txacon.gap.domain.customer.entities.Role;
import com.txacon.gap.domain.customer.entities.RoleName;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericDomainMapper<Role, RoleEntity> {

    @Mapping(target = "role", source = "name")
    Role toDomain(RoleEntity roleEntity);

    @Mapping(target = "name", source = "role")
    RoleEntity toEntity(Role role);


    default public RoleName toRoleName(String name){
        return RoleName.valueOf(name);
    }


    default String toStrRoleName(RoleName roleName){
        return roleName.name();
    }
}
