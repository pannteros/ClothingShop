package com.eshop.clothingshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Το όνομα είναι υποχρεωτικό")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Η τιμή είναι υποχρεωτική")
    @DecimalMin(value = "0.01", message = "Η τιμή πρέπει να είναι θετική")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotBlank(message = "Η κατηγορία είναι υποχρεωτική")
    private String category; // π.χ. "Μπλούζες", "Παντελόνια", "Φορέματα"

    private String size; // XS, S, M, L, XL, XXL

    private String color;

    @Min(value = 0, message = "Το απόθεμα δεν μπορεί να είναι αρνητικό")
    private int stock;

    private String imageUrl; // URL ή path εικόνας

    private boolean available = true;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
