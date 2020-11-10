package com.txacon.gap.domain.customer.domain;


import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    private final Long id;
    private final String email;
    private final String passwordHash;
    private String name;
    private String midName;
    private String lastName;
    private boolean active = true;
    private List<Address> addresses;
    private List<Phone> phones;
}
