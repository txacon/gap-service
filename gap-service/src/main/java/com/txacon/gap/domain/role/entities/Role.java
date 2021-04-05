package com.txacon.gap.domain.role.entities;

import static lombok.AccessLevel.PRIVATE;

import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Role implements Serializable {

  private Long roleId;

  @Enumerated(EnumType.STRING)
  private RoleName roleName;
}
