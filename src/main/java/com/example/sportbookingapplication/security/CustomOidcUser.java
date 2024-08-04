package com.example.sportbookingapplication.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
public class CustomOidcUser implements OidcUser {

    private final UserDetails userDetails;
    private final Collection<GrantedAuthority> authorities;

    public CustomOidcUser(UserDetails userDetails, Collection<GrantedAuthority> authorities) {
        this.userDetails = userDetails;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // Return user attributes from the OIDC provider
        return Collections.emptyMap();
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public OidcIdToken getIdToken() {
        // Implement as needed
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        // Implement as needed
        return null;
    }

    @Override
    public Map<String, Object> getClaims() {
        // Return claims
        return null;
    }
}

