package com.txacon.gap.domain.customer.entities;


import com.txacon.gap.domain.role.entities.Role;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    private Long id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private boolean active;
    private List<Address> addresses;
    private List<Phone> phones;
    private List<Role> roles;
}
