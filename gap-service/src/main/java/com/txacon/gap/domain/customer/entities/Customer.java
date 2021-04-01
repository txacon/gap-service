package com.txacon.gap.domain.customer.entities;

import static lombok.AccessLevel.PRIVATE;

import com.txacon.gap.domain.role.entities.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
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
