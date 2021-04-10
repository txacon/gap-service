package com.txacon.gap.infrastructure.rest.security;

import static com.txacon.gap.domain.security.SecurityConstants.AUTHORITY_CLAIMS;
import static com.txacon.gap.domain.security.SecurityConstants.HEADER_AUTHORIZACION_KEY;
import static com.txacon.gap.domain.security.SecurityConstants.ISSUER_INFO;
import static com.txacon.gap.domain.security.SecurityConstants.TOKEN_BEARER_PREFIX;
import static com.txacon.gap.domain.security.SecurityConstants.TOKEN_EXPIRATION_TIME;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.txacon.gap.domain.security.entities.JwtRequest;
import com.txacon.gap.domain.security.entities.JwtResponse;
import com.txacon.gap.domain.security.entities.JwtUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final String secret;
  private final AuthenticationManager authenticationManager;
  private final ObjectMapper objectMapper;

  public JWTAuthenticationFilter(
      AuthenticationManager authenticationManager, ObjectMapper objectMapper, String secret) {
    this.authenticationManager = authenticationManager;
    this.objectMapper = objectMapper;
    this.secret = secret;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      JwtRequest credenciales =
          new ObjectMapper().readValue(request.getInputStream(), JwtRequest.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      logger.error("Invalid credentials", e);
      throw new AuthenticationCredentialsNotFoundException("Invalid creadentials");
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication auth)
      throws IOException {

    Map<String, Object> authoritiesMap = new HashMap<>();
    authoritiesMap.put(
        AUTHORITY_CLAIMS,
        auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));

    String token =
        Jwts.builder()
            .setIssuedAt(new Date())
            .setIssuer(ISSUER_INFO)
            .setSubject(((JwtUserDetails) auth.getPrincipal()).getUsername())
            .addClaims(authoritiesMap)
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
            .compact();
    response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
    response.addHeader("Content-Type", "application/json");
    response.getWriter().println(objectMapper.writeValueAsString(new JwtResponse(token)));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
