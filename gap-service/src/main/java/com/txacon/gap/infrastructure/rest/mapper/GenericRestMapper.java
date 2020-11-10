package com.txacon.gap.infrastructure.rest.mapper;

public interface GenericRestMapper<DTO, DOMAIN> {

    DTO toDTO(DOMAIN entity);

    DOMAIN toDomain(DTO dto);
}
