package com.txacon.gap.infrastructure.rest.dto.customer;


import lombok.*;

import java.util.List;

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
    private String fistName;
    private String lastName;
    private boolean active;
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;
}
