package com.txacon.gap.infrastructure.db.jpa.product.mapper;

import com.txacon.gap.domain.products.entities.Product;
import com.txacon.gap.infrastructure.db.jpa.GenericDomainMapper;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import com.txacon.gap.infrastructure.db.jpa.tag.mapper.TagMapper;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface ProductMapper extends GenericDomainMapper<Product, ProductEntity> {

    default BusinessEntity toEntity(Long id) {
        if (id == null) return null;
        BusinessEntity businessEntity = new BusinessEntity();
        businessEntity.setId(id);
        return businessEntity;
    }

    default Long toId(BusinessEntity businessEntity) {
        return Objects.isNull(businessEntity) ? null : businessEntity.getId();
    }

}
