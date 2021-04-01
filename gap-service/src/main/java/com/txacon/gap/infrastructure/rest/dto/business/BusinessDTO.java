package com.txacon.gap.infrastructure.rest.dto.business;

import static lombok.AccessLevel.PRIVATE;

import com.txacon.gap.infrastructure.rest.dto.product.ProductDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class BusinessDTO {

  private Long id;
  private String fiscalId;
  private String name;
  private String phonePrefix;
  private String phone;
  private String street1;
  private String street2;
  private String city;
  private String zipcode;
  private String state;
  private String country;
  private String email;
  private String description;
  private String openHour;
  private String closeHour;
  private String aggregateRating;
  private String priceRange;
  private List<String> paymentMethods;
  private List<ProductDTO> products;
  private boolean active;

  private Long own;
}
