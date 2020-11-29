package com.txacon.gap.domain.products.entities;

import com.txacon.gap.domain.tags.entities.TagName;
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
    private Long businessId;
    private BigDecimal wholeSalePrice;
    private BigDecimal retailPrice;
    private String photoLink;
    private String name;
    private String description;
    private List<TagName> productTags;
    private boolean active;
}
