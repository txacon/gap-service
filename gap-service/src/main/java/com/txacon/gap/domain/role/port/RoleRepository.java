package com.txacon.gap.domain.role.port;

import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(RoleName roleName);

    Optional<Role> save(Role role);
}
