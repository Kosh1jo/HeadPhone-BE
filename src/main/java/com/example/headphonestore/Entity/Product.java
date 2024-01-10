package com.example.headphonestore.Entity;

import com.example.headphonestore.Shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private double price;
    private String image;
    private String brand;
    @Column(columnDefinition = "LONGTEXT")
    private String specification;
    private Status status;
}
