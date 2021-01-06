package com.txacon.gap.infrastructure.db.jpa.bussines.entites;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import com.txacon.gap.infrastructure.db.jpa.pricerange.entities.PriceRangeEntity;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import com.txacon.gap.infrastructure.db.jpa.rating.entities.AggregateRatingEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity(name = "business")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "fiscalId"})
@ToString
public class BusinessEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "business_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Column(unique = true)
    private String fiscalId;
    @Temporal(TemporalType.TIME)
    private Date openHour;
    @Temporal(TemporalType.TIME)
    private Date closeHour;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "aggregate_rating_id", nullable = true)
    private AggregateRatingEntity aggregateRating;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "price_range_id", nullable = true)
    private PriceRangeEntity priceRange;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity own;
    @Column(length = 1000)
    private String description;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", fetch = FetchType.EAGER)
    private final List<ProductEntity> products = new ArrayList<>();
    @Getter
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "business_payment_method", joinColumns = @JoinColumn(name = "business_id"), inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private final Set<PaymentMethodEntity> paymentMethods = new HashSet<>();

    public void setProducts(List<ProductEntity> products) {
        if (products == null)
            return;
        this.products.clear();
        products.forEach(e -> {
            e.setBusiness(this);
            this.getProducts().add(e);
        });
    }

    public void setPaymentMethods(Set<PaymentMethodEntity> paymentMethods) {
        if (paymentMethods == null)
            return;
        this.paymentMethods.clear();
        for (PaymentMethodEntity paymentMethod : paymentMethods) {
            paymentMethod.getBusinesses().add(this);
            this.paymentMethods.add(paymentMethod);
        }
    }
}
