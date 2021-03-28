package com.txacon.gap.infrastructure.rest.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

  private final PasswordEncoder passwordEncoder;

  @Named("passwordEncoding")
  public String encodePassword(String password) {
    if (password == null) {
      return null;
    }
    return passwordEncoder.encode(password);
  }
}
