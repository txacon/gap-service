package com.txacon.gap.infrastructure.rest.mapper;

public interface GenericRestMapper<D, T> {

  D toDTO(T entity);

  T toDomain(D dto);
}
