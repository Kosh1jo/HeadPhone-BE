package com.example.headphonestore.Repository;

import com.example.headphonestore.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);
    Product getProductByImageLike(String image);
    Product findProductByName(String name);
}