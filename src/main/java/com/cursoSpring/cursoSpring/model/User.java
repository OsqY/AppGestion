package com.cursoSpring.cursoSpring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "name")
    private String userName;
    @Getter @Setter @Column(name = "lastname")
    private String userLastName;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "phone")
    private String phoneNumber;
    @Getter @Setter @Column(name = "password")
    private String password;

}
