package com.project.fin.dto;
import com.project.fin.models.Role;

public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private Role role;
    public String getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
