package com.txacon.gap.infrastructure.db.jpa.customer.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CustomerPhone")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class CustomerPhone extends BaseEntity implements Serializable {

    @EmbeddedId
    private UserPhonesKey id;

    @ManyToOne
    @NonNull
    @Getter
    @Setter
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @NonNull
    @Getter
    @Setter
    @MapsId("phoneId")
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;

    @Getter
    @Setter
    @Column(name = "default_phone", columnDefinition = "boolean default false", nullable = false)
    private boolean defaultPhone = false;



    public CustomerPhone(CustomerEntity customer, PhoneEntity phone, boolean defaultPhone) {
        this.customer = customer;
        this.phone = phone;
        this.defaultPhone = defaultPhone;
    }


    @Data
    @Builder
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserPhonesKey implements Serializable {
        private Long customerId;
        private Long phoneId;
    }


}
