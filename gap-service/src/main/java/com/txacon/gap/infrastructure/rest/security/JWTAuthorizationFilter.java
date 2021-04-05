package com.txacon.gap.infrastructure.rest.security;

import static com.txacon.gap.domain.security.SecurityConstants.AUTHORITY_CLAIMS;
import static com.txacon.gap.domain.security.SecurityConstants.HEADER_AUTHORIZACION_KEY;
import static com.txacon.gap.domain.security.SecurityConstants.TOKEN_BEARER_PREFIX;

import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.domain.role.entities.Role;
import com.txacon.gap.domain.role.entities.RoleName;
import com.txacon.gap.domain.security.entities.MyGrantedAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private final String secret;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, String secret) {
    super(authenticationManager);
    this.secret = secret;
  }

  @Loggable
  @Override
  protected void doFilterInternal(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
    if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
    if (token == null) {
      return null;
    }

    Jws<Claims> jwtClaims =
        Jwts.parser()
            .setSigningKey(secret.getBytes())
            .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""));

    String user = jwtClaims.getBody().getSubject();
    List<GrantedAuthority> authorities = getAuthoritiesFromClaims(jwtClaims);
    if (user != null) {
      return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }
    return null;
  }

  private List<GrantedAuthority> getAuthoritiesFromClaims(Jws<Claims> jwtClaims) {
    if (jwtClaims == null) {
      log.info("Auth without claims");
      return new ArrayList<>();
    }
    List<String> roles = jwtClaims.getBody().get(AUTHORITY_CLAIMS, List.class);
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String e : roles) {
      try {
        MyGrantedAuthority myGrantedAuthority =
            new MyGrantedAuthority(Role.builder().roleName(RoleName.valueOf(e)).build());
        authorities.add(myGrantedAuthority);
      } catch (IllegalArgumentException ex) {
        log.warn("Illegal role name: {}", e);
      }
    }
    return authorities;
  }
}
