package com.urbanchic.config;

import com.urbanchic.config.filter.CsrfCookieFilter;
import com.urbanchic.config.filter.JwtVerificationFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
public class AuthConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtVerificationFilter jwtVerificationFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final PhoneNumberAuthenticationProvider phoneNumberAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/**"))
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(jwtVerificationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/**").permitAll()
                        //.requestMatchers("/api/v1/seller").hasAnyAuthority(Role.ROLE_SELLER.name())
                        .anyRequest().authenticated())
                .build();
    }

    //                     .requestMatchers("/users/**").hasAnyAuthority(Role.ROLE_BUYER.name())
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        // Register the custom provider for handling phone number authentication
        auth.authenticationProvider(phoneNumberAuthenticationProvider);
    }


}
