package com.strautins.CloneGag.model;

import com.strautins.CloneGag.model.validators.UniqueUsernameConstraint;
import com.strautins.CloneGag.security.CloneGagUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hibernate model, ideally we should extend User, but we cant as we cant have a default constructor in that case.
 */
@Entity
@Table(name = "USERS", schema = "gag")
public class CloneGagUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @UniqueUsernameConstraint
    @NotNull
    @NotEmpty
    @Column(name = "user_name", nullable = false)
    private String username;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private List<UserRole> roles = new ArrayList<>(0);

    public CloneGagUserDetails getUserDetails() {
        List<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRole())
        ).collect(Collectors.toList());

        return new CloneGagUserDetails(
                username,
                password,
                true,
                authorities,
                id
        );
    }

    public void addRole(UserRole role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
