package com.txacon.gap.infrastructure.rest.dto.product;

import static lombok.AccessLevel.PRIVATE;

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
public class ProductDTO {

  private Long id;
  private BigDecimal wholeSalePrice;
  private BigDecimal retailPrice;
  private String photoLink;
  private String name;
  private String description;
  private List<String> productTags;
  private boolean active;
}
