package com.txacon.gap.infrastructure.db.jpa.customer.entities;


import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class CustomerEntity extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Getter
    @Setter
    @NotEmpty(message = "*Please provide an name")
    private String name;
    @Getter
    @Setter
    private String midName;
    @Getter
    @Setter
    @NotEmpty(message = "*Please provide an lastName")
    private String lastName;

    @Getter
    @Setter
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "*Please provide your password")
    private String passwordHash;

    @Getter
    @Setter
    private boolean active=true;

    @OneToMany(
            mappedBy = "phone",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CustomerPhone> customerPhones;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CustomerAddress> customerAddresses;


    public Set<PhoneEntity> getCustomerPhones() {
        if (customerPhones == null) return Collections.EMPTY_SET;
        return customerPhones.stream().map(p -> p.getPhone()).collect(Collectors.toSet());
    }

    public Set<AddressEntity> getAddress() {
        if (customerAddresses == null) return Collections.EMPTY_SET;
        return customerAddresses.stream().map(a -> a.getAddress()).collect(Collectors.toSet());
    }


    public void addPhone(PhoneEntity phone, PhoneEntity... phones) {
        addDefaultPhone(phone);
        for (PhoneEntity varPhone : phones) {
            customerPhones.add(new CustomerPhone(this, varPhone, false));
        }
    }

    public void addDefaultPhone(PhoneEntity phone) {
        if (phone == null) return;
        if (customerPhones == null) customerPhones = new HashSet<>();
        else {
            for (CustomerPhone cPhone : customerPhones) {
                cPhone.setDefaultPhone(false);
            }
        }
        CustomerPhone inCustomerPhone = new CustomerPhone(this, phone, true);
        customerPhones.add(inCustomerPhone);
    }

    public void addAddress(AddressEntity address, AddressEntity... addresses) {
        addDefaultAddress(address);
        for (AddressEntity varAddress : addresses) {
            customerAddresses.add(new CustomerAddress(this, varAddress, false));
        }
    }

    private void addDefaultAddress(AddressEntity address) {
        if (address == null) return;
        if (customerAddresses == null) customerPhones = new HashSet<>();
        else {
            for (CustomerAddress cAddress : customerAddresses) {
                cAddress.setDefaultAddress(false);
            }
        }
        customerAddresses.add(new CustomerAddress(this, address, true));
    }


}
