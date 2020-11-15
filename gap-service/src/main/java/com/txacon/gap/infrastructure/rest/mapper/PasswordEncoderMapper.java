package com.txacon.gap.infrastructure.rest.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PasswordEncoderMapper {

    private PasswordEncoder passwordEncoder;

    @Named("passwordEncoding")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
