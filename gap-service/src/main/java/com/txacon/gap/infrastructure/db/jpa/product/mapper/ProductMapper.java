package com.txacon.gap.infrastructure.db.jpa.product.mapper;

import com.txacon.gap.domain.products.entities.Product;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import com.txacon.gap.infrastructure.db.jpa.tag.mapper.TagMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {TagMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper extends GenericDomainMapper<Product, ProductEntity> {


    void updateProduct(Product product, @MappingTarget ProductEntity entity);


}
