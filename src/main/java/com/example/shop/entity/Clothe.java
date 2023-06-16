package com.example.shop.entity;

import com.example.shop.validator.annotation.ValidCategoryId;
import com.example.shop.validator.annotation.ValidUserId;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "clothes")
public class Clothe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Name must not bo empty")
    @Size(max = 50, min = 1, message = "Title must be less than 50 characters")
    private String name;

    @Column(name= "image")
    private String image;

    @Column(name= "price")
    @NotNull(message = "Price is required")
    private Double price;

    @ManyToOne
    @JoinColumn(name= "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;

    @OneToMany(mappedBy = "clothe", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ItemInvoice> itemInvoices = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Clothe clothe = (Clothe) o;
        return getId() != null && Objects.equals(getId(), clothe.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
