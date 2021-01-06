package com.txacon.gap.infrastructure.db.jpa.customer.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import com.txacon.gap.infrastructure.db.jpa.bussines.entites.BusinessEntity;
import com.txacon.gap.infrastructure.db.jpa.role.entities.RoleEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity(name = "Customer")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = true)
@Table(name = "customer", indexes = {
        @Index(columnList = "email, password, isActive", name = "email_password_active_indx", unique = true),
        @Index(columnList = "email", name = "email_indx", unique = true),})
public class CustomerEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private final Set<PhoneEntity> phones = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private final Set<AddressEntity> addresses = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "own")
    private final Set<BusinessEntity> businesses = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private final Set<RoleEntity> roles = new HashSet<>();
    @NotEmpty(message = "*Please provide an name")
    private String username;
    private String firstName;
    private String lastName;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @NotEmpty(message = "*Please provide your password")
    private String password;
    private boolean isActive = true;
    private boolean isStaff = false;
    private boolean isSuperuser = false;
    private LocalDateTime lastLogin;

    public void setRoles(Set<RoleEntity> roles) {
        if (Objects.isNull(roles))
            return;
        this.roles.clear();
        for (RoleEntity role : roles) {
            role.addCustomer(this);
            this.roles.add(role);
        }
    }

    public void setPhones(Set<PhoneEntity> phones) {
        if (Objects.isNull(phones))
            return;
        this.phones.clear();
        for (PhoneEntity phone : phones) {
            phone.setCustomer(this);
            this.phones.add(phone);
        }
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        if (Objects.isNull(addresses))
            return;
        this.addresses.clear();
        for (AddressEntity address : addresses) {
            address.setCustomer(this);
            this.addresses.add(address);
        }
    }

}
