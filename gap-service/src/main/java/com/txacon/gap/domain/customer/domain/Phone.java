package com.txacon.gap.domain.customer.domain;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Phone {

    private final Long id;
    private final String coutryPrefix;
    private final String phoneNumber;
}
