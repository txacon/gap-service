package com.txacon.gap.infrastructure.db.jpa.customer.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CustomerAddress")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class CustomerAddress extends BaseEntity implements Serializable {

    @EmbeddedId
    private CustomerAddressKey id;

    @ManyToOne
    @Getter @Setter
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @Getter @Setter
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Getter @Setter
    @Column(name = "default_address", columnDefinition = "boolean default false", nullable = false)
    private boolean defaultAddress=false;

    @Builder
    public CustomerAddress(CustomerEntity customer, AddressEntity address, boolean defaultAddress) {
        this.customer = customer;
        this.address = address;
        this.defaultAddress = defaultAddress;
    }

    @Data
    @Builder
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CustomerAddressKey implements Serializable{
        private Long customerId;
        private Long addressId;
    }
}
