package com.strautins.CloneGag.definitions;

public enum Roles {

    USER("ROLE_USER");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
