package com.txacon.gap.domain.customer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private Long roleId;
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public enum RoleName {
        ROLE_ADMIN, ROLE_USER
    }
}
