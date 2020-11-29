package com.txacon.gap.infrastructure.db.jpa.bussines.entites;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "price_range")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class PriceRangeEntity extends BaseEntity implements Serializable {

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRange")
    Set<BusinessEntity> businesses = new HashSet<>();
    @Id
    @Getter
    @Column(name = "price_range_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String priceRangeName;

}
