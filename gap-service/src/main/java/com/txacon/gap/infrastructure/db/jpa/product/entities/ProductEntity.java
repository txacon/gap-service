package com.txacon.gap.infrastructure.db.jpa.product.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
@ToString
public class ProductEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;
    private BigDecimal wholeSalePrice;
    private BigDecimal retailPrice;
    private String photoLink;
    private String name;
    private String description;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<TagEntity> productTags;
    private boolean active;

    public void setProductTags(Set<TagEntity> productTags) {
        this.productTags.clear();
        for (TagEntity productTag : productTags) {
            productTag.getProducts().add(this);
            this.productTags.add(productTag);
        }
    }
}
