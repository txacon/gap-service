package com.txacon.gap.infrastructure.rest.dto;


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
    private String passwordHash;
    private String name;
    private String midName;
    private String lastName;
    private boolean active;
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;
}
