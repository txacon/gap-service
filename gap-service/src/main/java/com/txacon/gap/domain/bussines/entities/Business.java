package com.txacon.gap.domain.bussines.entities;

import com.txacon.gap.domain.common.entities.AggregateRating;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Business {

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
    private LocalTime openHour;
    private LocalTime closeHour;
    private AggregateRating aggregateRating;
    private PriceRange priceRange;
    private List<PaymentType> paymentMethods;
    private boolean active;

    private CustomerEntity own;
}
