package com.txacon.gap.infrastructure.db.jpa.payment.repository;

import com.txacon.gap.domain.payment.entities.PaymentType;
import com.txacon.gap.domain.payment.port.PaymentTypeRepository;
import com.txacon.gap.infrastructure.db.jpa.payment.mapper.PaymentMethodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PaymentMethodDatabaseRepository implements PaymentTypeRepository {

    private final CrudPaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    @Override
    public Optional<PaymentType> findByName(PaymentType name) {
        return repository.findById(name.name()).map(mapper::toDomain);
    }

    @Override
    public List<PaymentType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return mapper.toDomain(repository.save(mapper.toEntity(paymentType)));
    }
}
