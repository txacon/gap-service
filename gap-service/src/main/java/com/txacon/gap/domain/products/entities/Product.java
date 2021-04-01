package com.txacon.gap.domain.products.entities;

import static lombok.AccessLevel.PRIVATE;

import com.txacon.gap.domain.tags.entities.TagName;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
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
