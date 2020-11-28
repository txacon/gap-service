package com.txacon.gap.domain.security.entities;

import com.txacon.gap.domain.role.entities.Role;
import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {

    private final Role role;

    public MyGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getRole().name();
    }
}
