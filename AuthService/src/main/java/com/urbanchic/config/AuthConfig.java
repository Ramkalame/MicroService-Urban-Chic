package com.urbanchic.config;

import com.urbanchic.config.filter.CsrfCookieFilter;
import com.urbanchic.config.filter.JwtVerificationFilter;
import com.urbanchic.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    private  final CorsConfigurationSource corsConfigurationSource;
    private  final JwtVerificationFilter jwtVerificationFilter;
    private  final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

     return   httpSecurity
             .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))

             .cors(cors->cors.configurationSource(corsConfigurationSource))
             .csrf(csrf -> csrf
                     .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                     .ignoringRequestMatchers("/**"))
             .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler))
             .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
             .addFilterBefore(jwtVerificationFilter, BasicAuthenticationFilter.class)
             .authorizeHttpRequests( request-> request
                             .requestMatchers("/**").permitAll()
//                     .requestMatchers("/users/**").hasAnyAuthority(Role.ROLE_BUYER.name())
             )

             //.formLogin(Customizer.withDefaults())
             //.httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }


}
