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
public class PhoneDTO {

  private String countryPrefix;
  private String phoneNumber;
}
