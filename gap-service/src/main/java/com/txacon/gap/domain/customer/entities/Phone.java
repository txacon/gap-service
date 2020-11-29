package com.txacon.gap.domain.customer.entities;

import lombok.*;

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
