package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.BusinessService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.application.exceptions.ApiError;
import com.txacon.gap.application.exceptions.BusinessInvalidException;
import com.txacon.gap.application.exceptions.BusinessNotFoundException;
import com.txacon.gap.domain.bussines.entities.Business;
import com.txacon.gap.domain.bussines.port.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    @Loggable
    public Business findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessNotFoundException(ApiError.ERROR_BUSINESS_NOT_FOUND_BY_ID));
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
        if (business.getId() == null) throw new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_UPDATE);
        return saveBussines(business, idOwner);
    }

    @Override
    @Loggable
    public void deleteById(Long businessId) {
        repository.deleteById(businessId);
    }


    private Business saveBussines(Business business, Long idOwner) {
        if (Stream.of(business.getEmail(), business.getFiscalId(), business.getPhone(), business.getCity(), business.getZipcode(), business.getStreet1()).anyMatch(Objects::isNull))
            throw new BusinessInvalidException(ApiError.ERROR_BUSINESS_INVALID_TO_CREATE);
        business.setOwn(idOwner);
        return repository.save(business);
    }
}
