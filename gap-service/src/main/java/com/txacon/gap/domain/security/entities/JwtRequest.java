package com.txacon.gap.domain.security.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

  private static final long serialVersionUID = -1785582702668216278L;
  private String username;
  private String password;
}
