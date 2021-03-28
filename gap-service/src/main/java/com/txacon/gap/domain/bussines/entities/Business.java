package com.txacon.gap.domain.bussines.entities;

import com.txacon.gap.domain.payment.entities.PaymentType;
import com.txacon.gap.domain.pricerange.entities.PriceRange;
import com.txacon.gap.domain.products.entities.Product;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
  private List<Product> products;
  private boolean active;

  private Long own;
}
