package com.strautins.CloneGag.model;

import com.strautins.CloneGag.definitions.Roles;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "USER_ROLES", schema = "gag")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    public UserRole() {
    }

    public UserRole(Roles role) {
        this.role = role.getRole();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
