package com.txacon.gap.domain.products.port;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.products.entities.Product;
import com.txacon.gap.domain.tags.entities.TagName;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  Optional<Product> findById(Long id);

  List<Product> findByBusiness(Business business);

  List<Product> findByBusinessAndProductTag(Business business, TagName tagName);

  Product save(Product product);
}
