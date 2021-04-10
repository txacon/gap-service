package com.txacon.gap.infrastructure.db.jpa.role.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "role_id")
@Table(name = "role")
public class RoleEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -1415232412964430211L;
  @ToString.Exclude
  @ManyToMany(mappedBy = "roles")
  private final Set<CustomerEntity> customers = new HashSet<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long roleId;
  @Column(name = "name", unique = true)
  private String name;

  public Set<CustomerEntity> getCustomers() {
    return customers;
  }

  public void setCustomers(Set<CustomerEntity> customers) {
    this.customers.clear();
    this.customers.addAll(customers);
  }

  public void addCustomer(CustomerEntity customerEntity) {
    this.customers.add(customerEntity);
  }
}
