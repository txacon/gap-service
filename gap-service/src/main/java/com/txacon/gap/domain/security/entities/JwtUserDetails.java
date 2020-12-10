package com.txacon.gap.domain.security.entities;

import com.txacon.gap.domain.customer.entities.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserDetails implements UserDetails {

    private final List<GrantedAuthority> grantedAuthorities;
    private final String username;
    private final String password;
    private final boolean active;


    public JwtUserDetails(Customer customer) {
        this.grantedAuthorities = customer.getRoles().stream().map(r -> new MyGrantedAuthority(r)).collect(Collectors.toList());
        this.username = customer.getId() + "";
        this.password = customer.getPassword();
        this.active = customer.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
