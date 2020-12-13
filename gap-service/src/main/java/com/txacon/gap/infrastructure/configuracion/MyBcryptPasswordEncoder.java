package com.txacon.gap.infrastructure.configuracion;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

public class MyBcryptPasswordEncoder extends BCryptPasswordEncoder {
    private final Pattern MY_BCRYPT_PATTERN = Pattern
            .compile("bcrypt\\$\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

    public MyBcryptPasswordEncoder(BCryptVersion version, int strength) {
        super(version, strength);
        this.BCRYPT_PATTERN=MY_BCRYPT_PATTERN;
    }
}
