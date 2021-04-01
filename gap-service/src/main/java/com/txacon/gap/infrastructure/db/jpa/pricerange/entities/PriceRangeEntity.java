package com.txacon.gap.infrastructure.db.jpa.pricerange.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "price_range")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class PriceRangeEntity extends BaseEntity implements Serializable {

  @ToString.Exclude
  @Getter
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRange")
  private final Set<BusinessEntity> businesses = new HashSet<>();
  @Id
  @Column(name = "price_range_id")
  private String id;
}
