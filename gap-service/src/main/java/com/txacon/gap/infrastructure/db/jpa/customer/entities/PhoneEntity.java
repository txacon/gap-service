package com.txacon.gap.infrastructure.db.jpa.customer.entities;

import com.txacon.gap.infrastructure.db.jpa.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Phone")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PhoneEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    private Long id;
    private String coutryPrefix;
    private String phoneNumber;

}
