package com.txacon.gap.application.api;

import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.products.entities.Product;

import java.util.List;

public interface BusinessService {

    List<Business> findAll();

    Business findById(Long id);

    List<Business> findByOwner(Customer owner);

    List<Business> findByOwnerId(Long idOwner);

    Business add(Business business, Long idOwner);

    Business update(Business business, Long idOwner);

    void deleteById(Long businessId);

    Product addBussinessProduct(Long bussinessId, Product product);

    Product updateBussinessProduct(Long bussinessId, Product product);

    void deleteProduct(Long bussinessId, Product product);

}
