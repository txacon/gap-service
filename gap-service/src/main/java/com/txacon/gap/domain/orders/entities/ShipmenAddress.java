package com.txacon.gap.domain.orders.entities;

import lombok.Data;

@Data
public class ShipmenAddress {

  private Long id;
  private String street1;
  private String street2;
  private String state;
  private String city;
  private String country;
  private Integer zipcode;
}
