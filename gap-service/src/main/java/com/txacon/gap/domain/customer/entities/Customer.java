package com.txacon.gap.domain.customer.entities;


import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String midName;
    private String lastName;
    private boolean active;
    private List<Address> addresses;
    private List<Phone> phones;
}
