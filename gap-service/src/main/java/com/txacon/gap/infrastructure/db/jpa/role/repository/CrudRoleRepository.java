package com.txacon.gap.infrastructure.db.jpa.role.repository;

import com.txacon.gap.infrastructure.db.jpa.role.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CrudRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String roleName);

}
