package com.txacon.gap.domain.customer.entities;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(of = {"street1", "street2", "state", "city", "country", "zipcode"})
public class Address {

  private Long id;
  private String street1;
  private String street2;
  private String state;
  private String city;
  private String country;
  private Integer zipcode;
}
