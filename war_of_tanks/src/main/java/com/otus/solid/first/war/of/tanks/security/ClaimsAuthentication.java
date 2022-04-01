package com.otus.solid.first.war.of.tanks.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClaimsAuthentication implements Authentication {
    private final Claims claims;
    private final List<Role> roles;

    public ClaimsAuthentication(Claims claims) {
        this.claims = claims;

        var roles = new ArrayList<Role>();
        if (claims.get("isCan", Boolean.class)) {
            roles.add(Role.GAMER);
        } else {
            roles.add(Role.NOT_GAMER);
        }

        this.roles = Collections.unmodifiableList(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return claims.getSubject();
    }
}
