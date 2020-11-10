package com.txacon.gap.domain.customer.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Phone {

    private final Long id;
    private final String coutryPrefix;
    private final String phoneNumber;
}
