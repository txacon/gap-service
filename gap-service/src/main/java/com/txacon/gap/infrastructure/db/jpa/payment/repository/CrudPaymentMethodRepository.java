package com.txacon.gap.infrastructure.db.jpa.payment.repository;

import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CrudPaymentMethodRepository extends JpaRepository<PaymentMethodEntity, String> {
}
