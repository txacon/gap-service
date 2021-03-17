package com.txacon.gap.infrastructure.rest.dto.customer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
