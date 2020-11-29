package com.txacon.gap.application.api;

import com.txacon.gap.domain.bussines.entities.Business;

import java.util.List;

public interface BusinessService {

    List<Business> findAll();

    Business findById(Long id);

    Business add(Business business, Long idOwner);

    Business update(Business business, Long idOwner);

}
