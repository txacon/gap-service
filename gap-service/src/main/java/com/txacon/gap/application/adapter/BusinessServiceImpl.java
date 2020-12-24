package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.BusinessService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.application.exceptions.ApiError;
import com.txacon.gap.application.exceptions.BusinessInvalidException;
import com.txacon.gap.application.exceptions.BusinessNotFoundException;
import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.bussines.port.BusinessRepository;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.products.entities.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository repository;

    @Override
    @Loggable
    public List<Business> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Business> findByOwnerId(Long idOwner) {
        List<Business> findByOwnId = repository.findByOwnId(idOwner);
        return findByOwnId;
    }

    @Override
    public List<Business> findByOwner(Customer owner) {
        return repository.findByOwn(owner);
    }

    @Override
    @Loggable
    public Business findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessNotFoundException(ApiError.ERROR_BUSINESS_NOT_FOUND_BY_ID));
    }

    @Override
    @Loggable
    public Business add(Business business, Long idOwner) {
        business.setId(null);
        return saveBussines(business, idOwner);
    }

    @Override
    @Loggable
    public Business update(Business business, Long idOwner) {
        if (business.getId() == null)
            throw new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_UPDATE);
        return saveBussines(business, idOwner);
    }

    @Override
    @Loggable
    public void deleteById(Long businessId) {
        repository.deleteById(businessId);
    }

    @Override
    public Product addBussinessProduct(Long businessId, Product product) {
        Business bussiness = repository.findById(businessId)
                .orElseThrow(() -> new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_UPDATE));
        product.setId(null);
        bussiness.getProducts().add(product);
        repository.save(bussiness);
        return product;
    }

    @Override
    public Product updateBussinessProduct(Long bussinessId, Product product) {
        Business bussiness = repository.findById(bussinessId)
                .orElseThrow(() -> new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_UPDATE));
        if (product.getId() == null)
            throw new BusinessInvalidException(ApiError.PRODUCT_INVALID_TO_UPDATE);
        bussiness.getProducts().add(product);
        repository.save(bussiness);
        return product;

    }

    @Override
    public void deleteProduct(Long businessId, Product product) {
        Business business = repository.findById(businessId)
                .orElseThrow(() -> new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_UPDATE));
        if (product.getId() == null)
            throw new BusinessInvalidException(ApiError.PRODUCT_INVALID_TO_UPDATE);
        business.getProducts().removeIf(e -> e.getId().equals(product.getId()));
        repository.save(business);
    }

    private Business saveBussines(Business business, Long idOwner) {
        if (Stream.of(business.getEmail(), business.getFiscalId(), business.getPhone(), business.getCity(),
                business.getZipcode(), business.getStreet1()).anyMatch(Objects::isNull))
            throw new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_CREATE);
        business.setOwn(idOwner);
        return repository.save(business);
    }

}
