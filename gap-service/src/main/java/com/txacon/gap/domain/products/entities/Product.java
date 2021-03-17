package com.txacon.gap.domain.products.entities;

import com.txacon.gap.domain.tags.entities.TagName;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

  private Long id;
  private BigDecimal wholeSalePrice;
  private BigDecimal retailPrice;
  private String photoLink;
  private String name;
  private String description;
  private List<TagName> productTags;
  private boolean active;
}
