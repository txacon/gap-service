package com.txacon.gap.infrastructure.rest.dto.business;

import lombok.*;

import java.util.List;

import com.txacon.gap.infrastructure.rest.dto.product.ProductDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BusinessDTO {

    private Long id;
    private String fiscalId;
    private String name;
    private String phonePrefix;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String zipcode;
    private String state;
    private String country;
    private String email;
    private String description;
    private String openHour;
    private String closeHour;
    private String aggregateRating;
    private String priceRange;
    private List<String> paymentMethods;
    private List<ProductDTO> productDTOs;
    private boolean active;

    private Long own;
}
