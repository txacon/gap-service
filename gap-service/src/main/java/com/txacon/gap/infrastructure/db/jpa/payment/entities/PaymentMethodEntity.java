package com.txacon.gap.infrastructure.db.jpa.payment.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "payment_method")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class PaymentMethodEntity extends BaseEntity implements Serializable {

  @Id
  @Column(name = "payment_method_id")
  private String id;

  @Getter
  @ManyToMany(mappedBy = "paymentMethods")
  private Set<BusinessEntity> businesses = new HashSet<>();
}
