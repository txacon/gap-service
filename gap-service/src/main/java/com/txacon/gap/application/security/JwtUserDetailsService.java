package com.txacon.gap.application.security;

import com.txacon.gap.application.security.entities.JwtUserDetails;
import com.txacon.gap.domain.customer.entities.Customer;
import com.txacon.gap.domain.customer.port.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerRepository respository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = respository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        return new JwtUserDetails(customer);
    }


}
