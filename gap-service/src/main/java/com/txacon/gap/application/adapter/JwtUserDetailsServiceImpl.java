package com.txacon.gap.application.adapter;

import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import com.txacon.gap.domain.security.entities.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  private final CustomerRepository respository;

  @Override
  @Loggable
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer =
        respository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    return new JwtUserDetails(customer);
  }
}
