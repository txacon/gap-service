package com.txacon.gap.infrastructure.db.jpa.rating.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "aggregate_rating")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class AggregateRatingEntity extends BaseEntity implements Serializable {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aggregate_rating_id")
    private Long id;
    @Getter
    @Setter
    private String aggregateRatingName;
    @Getter
    @Setter
    private String description;
    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aggregateRating")
    private Set<BusinessEntity> businesses;
}
