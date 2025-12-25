package com.project.fin.dto;

import com.project.fin.models.Role;

public class LoginDto {
    private String email;
    private String password;
//    private Role role;
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
//    public Role getRole() {
//        return role;
//    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
