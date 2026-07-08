package com.eshop.clothingshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Το username είναι υποχρεωτικό")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Το email είναι υποχρεωτικό")
    @Email(message = "Μη έγκυρο email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Ο κωδικός είναι υποχρεωτικός")
    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public enum Role {
        USER, ADMIN
    }
}
