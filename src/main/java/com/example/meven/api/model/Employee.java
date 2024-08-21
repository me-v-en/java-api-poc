package com.example.meven.api.model;

import jakarta.persistence.*;
import lombok.Data;

// Lombox annotation to generate automically getters & setters
@Data

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String mail;

    private String password;
}
