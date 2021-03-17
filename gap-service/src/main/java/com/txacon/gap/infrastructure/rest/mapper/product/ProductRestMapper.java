package com.txacon.gap.infrastructure.rest.mapper.product;

import com.txacon.gap.domain.products.entities.Product;
import com.txacon.gap.infrastructure.rest.dto.product.ProductDTO;
import com.txacon.gap.infrastructure.rest.mapper.GenericRestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper extends GenericRestMapper<ProductDTO, Product> {

}
