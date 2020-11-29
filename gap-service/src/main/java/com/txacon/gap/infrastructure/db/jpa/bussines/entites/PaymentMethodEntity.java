package com.txacon.gap.infrastructure.db.jpa.bussines.entites;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "payment_method")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class PaymentMethodEntity extends BaseEntity implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long id;
    @Getter
    @Setter
    private String paymentType;
    @Getter
    @Setter
    private String description;
    @Getter
    @ManyToMany(mappedBy = "paymentMethods")
    private Set<BusinessEntity> businesses;

}
