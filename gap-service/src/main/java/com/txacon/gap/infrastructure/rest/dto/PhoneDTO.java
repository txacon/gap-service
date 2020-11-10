package com.txacon.gap.infrastructure.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class PhoneDTO {

    private String countryPrefix;
    private String phoneNumber;
}
