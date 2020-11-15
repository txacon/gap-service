package com.txacon.gap.application.security.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtRequest {

    private final String username;
    private final String password;
}
