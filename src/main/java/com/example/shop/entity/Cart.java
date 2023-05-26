package com.example.shop.entity;

import com.example.shop.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_clothe", referencedColumnName = "id")
    @ValidUserId
    private Clothe clothe;

    @ManyToOne
    @JoinColumn(name="id_user", referencedColumnName = "id")
    @ValidUserId
    private User user;

    @Column(name = "quantity")
    private int quantity;
}
