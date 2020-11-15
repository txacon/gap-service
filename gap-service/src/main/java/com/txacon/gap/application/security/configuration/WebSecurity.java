package com.txacon.gap.application.security.configuration;


import com.txacon.gap.application.security.JWTAuthenticationFilter;
import com.txacon.gap.application.security.JWTAuthorizationFilter;
import com.txacon.gap.application.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.txacon.gap.application.security.SecurityConstants.LOGIN_URL;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService userDetailsService;

    @Value("jwt.secret")
    private String secret;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /*
         * 1. Se desactiva el uso de cookies
         * 2. Se activa la configuración CORS con los valores por defecto
         * 3. Se desactiva el filtro CSRF
         * 4. Se indica que el login no requiere autenticación
         * 5. Se indica que el resto de URLs esten securizadas
         */
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/swagger-ui",
                "/swagger-ui/**",
                "/webjars/**").permitAll().and()
                .authorizeRequests().antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), secret))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), secret));
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
