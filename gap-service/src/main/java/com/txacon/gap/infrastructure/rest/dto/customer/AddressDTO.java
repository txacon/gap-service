package com.txacon.gap.infrastructure.rest.dto.customer;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class AddressDTO {

  private String street1;
  private String street2;
  private String state;
  private String city;
  private String country;
  private Integer zipcode;
}
