package com.txacon.gap.infrastructure.db.jpa.role.repository;

import com.txacon.gap.infrastructure.db.jpa.role.entities.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CrudRoleRepository extends JpaRepository<RoleEntity, Long> {

  Optional<RoleEntity> findByName(String roleName);
}
