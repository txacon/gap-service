package com.txacon.gap.infrastructure.db.jpa.bussines.entites;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.customer.entities.CustomerEntity;
import com.txacon.gap.infrastructure.db.jpa.payment.entities.PaymentMethodEntity;
import com.txacon.gap.infrastructure.db.jpa.pricerange.entities.PriceRangeEntity;
import com.txacon.gap.infrastructure.db.jpa.product.entities.ProductEntity;
import com.txacon.gap.infrastructure.db.jpa.rating.entities.AggregateRatingEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "business")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "fiscalId"}, callSuper = false)
public class BusinessEntity extends BaseEntity implements Serializable {

  @ToString.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "business", fetch = FetchType.EAGER)
  private final List<ProductEntity> products = new ArrayList<>();
  @Getter
  @ToString.Exclude
  @ManyToMany(
      cascade = {CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "business_payment_method",
      joinColumns = @JoinColumn(name = "business_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
  private final Set<PaymentMethodEntity> paymentMethods = new HashSet<>();
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

  public void setProducts(List<ProductEntity> products) {
    if (products == null) {
      return;
    }
    this.products.clear();
    products.forEach(this::addProduct);
  }

  public void addProduct(ProductEntity productEntity) {
    productEntity.setBusiness(this);
    this.products.add(productEntity);
  }

  public void setPaymentMethods(Set<PaymentMethodEntity> paymentMethods) {
    if (paymentMethods == null) {
      return;
    }
    this.paymentMethods.clear();
    paymentMethods.forEach(this::addPaymentMethod);
  }

  public void addPaymentMethod(PaymentMethodEntity paymentMethodEntity) {
    paymentMethodEntity.getBusinesses().add(this);
    this.paymentMethods.add(paymentMethodEntity);
  }
}
