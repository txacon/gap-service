package com.txacon.gap.infrastructure.db.jpa.rating.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aggregateRating")
    private Set<BusinessEntity> businesses = new HashSet<>();
}
