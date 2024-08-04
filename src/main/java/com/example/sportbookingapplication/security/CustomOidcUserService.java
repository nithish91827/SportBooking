package com.example.sportbookingapplication.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomOidcUserService extends OidcUserService {

    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);
        OidcUserInfo userInfo = oidcUser.getUserInfo();

        // Extract user information
        String email = userInfo.getEmail();
        String name = userInfo.getFullName();

        // Create authorities or roles based on your application's requirements
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("SCOPE_READ"));

        // Create UserDetails object
        UserDetails userDetails = User.builder()
                .username(email)
                .password("") // Not used
                .roles("READ") // Assign roles here
                .build();

        return new CustomOidcUser(userDetails, authorities);
    }
}

