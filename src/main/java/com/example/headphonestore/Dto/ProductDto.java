package com.example.headphonestore.Dto;

import com.example.headphonestore.Entity.BillDetail;
import com.example.headphonestore.Entity.Category;
import com.example.headphonestore.Shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long id;
    private long categoryId;
    private String categoryName;
    private String name;
    private String description;
    private double price;
    private String image;
    private String brand;
    private String specification;
    private Status status;
    private MultipartFile imageFile;
}
