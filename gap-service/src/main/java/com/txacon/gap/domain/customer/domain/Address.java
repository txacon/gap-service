package com.txacon.gap.domain.customer.domain;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {

    private final Long id;
    private String street1;
    private String street2;
    private String state;
    private String city;
    private String country;
    private Integer zipcode;
}
