package com.txacon.gap.domain.products.entities;

import com.txacon.gap.domain.bussines.entities.Business;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    private Long id;
    private Business business;
    private BigDecimal wholeSalePrice;
    private BigDecimal retailPrice;
    private String photoLink;
    private String name;
    private String description;
    private List<TagName> productTags;
    private boolean active;
}
