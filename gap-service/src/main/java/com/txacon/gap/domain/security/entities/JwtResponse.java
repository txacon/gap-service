package com.txacon.gap.domain.security.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

  private static final long serialVersionUID = -1874235246649529094L;
  private String token;
}
