package com.txacon.gap.infrastructure.rest.dto.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDTO {

    private Long id;
    private Long businessId;
    private BigDecimal wholeSalePrice;
    private BigDecimal retailPrice;
    private String photoLink;
    private String name;
    private String description;
    private List<String> productTags;
    private boolean active;
}
