package com.txacon.gap.infrastructure.db.jpa;

public interface GenericDomainMapper<DOMAIN, ENTITY> {

  DOMAIN toDomain(ENTITY entity);

  ENTITY toEntity(DOMAIN domain);
}
