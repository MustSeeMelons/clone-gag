package com.strautins.CloneGag.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.Collection;

public class CloneGagUserDetails extends User {

    private BigInteger id;

    public CloneGagUserDetails(String username,
                               String password,
                               boolean enabled,
                               Collection<? extends GrantedAuthority> authorities,
                               BigInteger id) {
        super(username, password, enabled, true, true, true, authorities);
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CloneGagUserDetails{" +
                "id=" + id +
                '}';
    }
}
