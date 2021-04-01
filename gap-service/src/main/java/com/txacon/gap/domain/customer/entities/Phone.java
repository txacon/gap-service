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
@EqualsAndHashCode(of = {"countryPrefix", "phoneNumber"})
public class Phone {

  private Long id;
  private String countryPrefix;
  private String phoneNumber;
}
