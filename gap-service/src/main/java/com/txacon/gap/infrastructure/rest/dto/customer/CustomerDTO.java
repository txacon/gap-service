package com.txacon.gap.infrastructure.rest.dto.customer;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class CustomerDTO {

  private Long id;
  private String email;
  private String password;
  private String username;
  private String firstName;
  private String lastName;
  private boolean active;
  private List<AddressDTO> addresses;
  private List<PhoneDTO> phones;
}
