package com.urbanchic.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneNumberAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public PhoneNumberAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true); // Must call this otherwise it will be unauthenticated
    }

    @Override
    public Object getCredentials() {
        return null;
    }


    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
