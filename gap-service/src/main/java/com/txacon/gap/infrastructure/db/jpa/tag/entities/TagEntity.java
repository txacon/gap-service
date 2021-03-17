package com.txacon.gap.infrastructure.db.jpa.tag.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
  private String tagName;
}
