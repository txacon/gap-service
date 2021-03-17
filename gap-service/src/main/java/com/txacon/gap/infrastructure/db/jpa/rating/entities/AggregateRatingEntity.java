package com.txacon.gap.infrastructure.db.jpa.rating.entities;

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
@Entity(name = "aggregate_rating")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class AggregateRatingEntity extends BaseEntity implements Serializable {

  @Id
  @Getter
  @Column(name = "aggregate_rating_id")
  private String id;

  @Getter
  @ToString.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "aggregateRating")
  private Set<BusinessEntity> businesses = new HashSet<>();
}
