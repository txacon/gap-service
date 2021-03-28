package com.txacon.gap.infrastructure.db.jpa.role.repository;

import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;
import com.txacon.gap.domain.role.port.RoleRepository;
import com.txacon.gap.infrastructure.db.jpa.role.mapper.RoleMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleDatabaseRepository implements RoleRepository {

  private final CrudRoleRepository repository;
  private final RoleMapper mapper;

  @Override
  public Optional<Role> findByName(RoleName roleName) {
    return repository.findByName(roleName.name()).map(mapper::toDomain);
  }

  @Override
  public Optional<Role> save(Role role) {
    return Optional.ofNullable(mapper.toDomain(repository.save(mapper.toEntity(role))));
  }
}
