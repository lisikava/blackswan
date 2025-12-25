package com.project.fin.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Shop shop;
    public User() {}
    public User(String username, String email, String password, Role role, Shop shop) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.shop = shop;
    }

    public Long getId() {
        return id;
    }
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
    public Shop getShop() {
        return shop;
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
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
