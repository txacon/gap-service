package com.txacon.gap.application.adapter;

import com.txacon.gap.application.api.KeyService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.application.exceptions.ApiError;
import com.txacon.gap.application.exceptions.EntityNotFoundException;
import com.txacon.gap.domain.common.port.KeyEntityRepository;

import java.util.List;
import java.util.Optional;

abstract class KeyAbstractService<T> implements KeyService<T> {

    private final KeyEntityRepository<T> repository;

    protected KeyAbstractService(KeyEntityRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Loggable
    public T findByName(T name) {
        return repository.findByName(name).orElseThrow(() -> new EntityNotFoundException(ApiError.ERROR_ENTITY_NOT_FOUND_BY_NAME));
    }

    @Override
    @Loggable
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Loggable
    public T add(T name) {
        Optional<T> byName = repository.findByName(name);
        if (byName.isPresent()) return byName.get();
        return repository.save(name);
    }
}
