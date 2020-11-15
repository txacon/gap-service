package com.txacon.gap.application.security.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {

    private final String jwtToken;
}
