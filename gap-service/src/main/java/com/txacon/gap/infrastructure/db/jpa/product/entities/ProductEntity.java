package com.txacon.gap.infrastructure.db.jpa.product.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.tag.entities.TagEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
public class ProductEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 2150665180895681686L;
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

  @Column(length = 1000)
  private String description;

  @ManyToMany(
      cascade = {CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "product_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<TagEntity> productTags = new ArrayList<>();

  private boolean active;

  public void setProductTags(List<TagEntity> productTags) {
    if (Objects.isNull(productTags)) {
      return;
    }
    this.productTags.clear();
    for (TagEntity productTag : productTags) {
      productTag.getProducts().add(this);
      this.productTags.add(productTag);
    }
  }
}
