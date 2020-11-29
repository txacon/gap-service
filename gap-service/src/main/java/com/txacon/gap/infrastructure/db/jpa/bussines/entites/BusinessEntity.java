package com.txacon.gap.infrastructure.db.jpa.bussines.entites;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import com.txacon.gap.infrastructure.db.jpa.pricerange.entities.PriceRangeEntity;
import com.txacon.gap.infrastructure.db.jpa.rating.entities.AggregateRatingEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "business")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "fiscalId"})
@ToString
public class BusinessEntity extends BaseEntity implements Serializable {

    @Getter
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "business_payment_method",
            joinColumns = @JoinColumn(name = "business_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private final Set<PaymentMethodEntity> paymentMethods = new HashSet<>();
    @Id
    @Column(name = "business_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fiscalId;
    private String name;
    private String phonePrefix;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String zipcode;
    private String state;
    private String country;
    private String email;
    private String description;
    @Temporal(TemporalType.TIME)
    private Date openHour;
    @Temporal(TemporalType.TIME)
    private Date closeHour;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aggregate_rating_id", nullable = false)
    private AggregateRatingEntity aggregateRating;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_range_id", nullable = false)
    private PriceRangeEntity priceRange;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity own;

    public void setPaymentMethods(Set<PaymentMethodEntity> paymentMethods) {
        this.paymentMethods.clear();
        for (PaymentMethodEntity paymentMethod : paymentMethods) {
            paymentMethod.getBusinesses().add(this);
            this.paymentMethods.add(paymentMethod);
        }
    }
}
