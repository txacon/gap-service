package com.txacon.gap.infrastructure.db.jpa.payment.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "payment_method")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class PaymentMethodEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "payment_method_id")
    private String id;
    @Getter
    @ManyToMany(mappedBy = "paymentMethods")
    private Set<BusinessEntity> businesses = new HashSet<>();

}
