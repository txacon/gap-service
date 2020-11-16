package com.txacon.gap.infrastructure.db.jpa.customer.entities;


import com.txacon.gap.domain.customer.entities.Address;
import com.txacon.gap.domain.customer.entities.Role;
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
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = true)
@Table(name = "customer",
        indexes = {
                @Index(columnList = "email, passwordHash, active", name = "email_password_active_indx", unique = true),
                @Index(columnList = "email", name = "email_indx", unique = true),
        }
)
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
    private boolean active = true;

    @Getter
    @OneToMany(cascade = CascadeType.ALL,mappedBy="customer")
    private Set<PhoneEntity> phones = new HashSet<>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy="customer")
    private Set<AddressEntity> addresses = new HashSet<>();

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(
            name = "customer_role",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();



    public void setRoles(Set<RoleEntity> roles) {
        this.roles.clear();
        for (RoleEntity role : roles) {
            role.addCustomer(this);
            this.roles.add(role);
        }
    }

    public void setPhones(Set<PhoneEntity> phones) {
        this.phones.clear();
        for (PhoneEntity phone : phones) {
            phone.setCustomer(this);
            this.phones.add(phone);
        }
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses.clear();
        for (AddressEntity address : addresses) {
            address.setCustomer(this);
            this.addresses.add(address);
        }

    }
}
