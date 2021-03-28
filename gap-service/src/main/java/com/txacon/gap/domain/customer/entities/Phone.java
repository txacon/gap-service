package com.txacon.gap.domain.customer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"countryPrefix", "phoneNumber"})
@ToString
public class Phone {

  private Long id;
  private String countryPrefix;
  private String phoneNumber;
}
