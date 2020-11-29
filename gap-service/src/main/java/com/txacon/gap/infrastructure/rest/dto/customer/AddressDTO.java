package com.txacon.gap.infrastructure.rest.dto.customer;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddressDTO {

    private String street1;
    private String street2;
    private String state;
    private String city;
    private String country;
    private Integer zipcode;
}
