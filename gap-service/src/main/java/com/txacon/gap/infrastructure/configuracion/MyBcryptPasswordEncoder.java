package com.txacon.gap.infrastructure.configuracion;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyBcryptPasswordEncoder implements PasswordEncoder {

  private static final String PREFIX_BCRYPT = "bcrypt$";

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MyBcryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion version, int strength) {
    bCryptPasswordEncoder = new BCryptPasswordEncoder(version, strength);
  }

  @Override
  public String encode(CharSequence rawPassword) {
    String encode = bCryptPasswordEncoder.encode(rawPassword);
    return PREFIX_BCRYPT + encode;
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    String customEncodedPassword = encodedPassword.replace(PREFIX_BCRYPT, "");
    return bCryptPasswordEncoder.matches(rawPassword, customEncodedPassword);
  }
}
