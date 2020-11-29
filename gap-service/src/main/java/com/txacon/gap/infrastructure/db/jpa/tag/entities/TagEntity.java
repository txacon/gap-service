package com.txacon.gap.infrastructure.db.jpa.tag.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class TagEntity extends BaseEntity implements Serializable {

    @ManyToMany(mappedBy = "productTags")
    private final Set<ProductEntity> products = new HashSet<>();
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;
}
