package com.project.fin.models;

import jakarta.persistence.*;

@Entity
@Table
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;
    private boolean aiTags;
}
