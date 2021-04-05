package com.txacon.gap.infrastructure.db.jpa;

public interface GenericDomainMapper<D, E> {

  D toDomain(E entity);

  E toEntity(D domain);
}
