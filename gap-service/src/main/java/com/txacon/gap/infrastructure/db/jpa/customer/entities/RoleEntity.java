package com.txacon.gap.infrastructure.db.jpa.customer.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_roles")
public class RoleEntity extends BaseEntity {

    @ManyToMany(mappedBy = "roles")
    Set<CustomerEntity> customers;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;
    private String name;
}
