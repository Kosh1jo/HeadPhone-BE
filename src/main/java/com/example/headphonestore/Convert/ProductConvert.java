package com.example.headphonestore.Convert;

import com.example.headphonestore.Dto.ProductDto;
import com.example.headphonestore.Entity.Product;
import com.example.headphonestore.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
    @Autowired
    private CategoryRepository categoryRepository;
    public ProductDto toProductDTO(Product product) {
        ProductDto result = new ProductDto();
        if(product.getId() != null){
            result.setId(product.getId());
        }
        result.setName(product.getName());
        result.setPrice(product.getPrice());
        result.setBrand(product.getBrand());
        result.setImage(product.getImage());
        result.setDescription(product.getDescription());
        result.setCategoryId(product.getCategory().getId());
        result.setCategoryName(product.getCategory().getName());
        result.setSpecification(product.getSpecification());
        result.setStatus(product.getStatus());
        return result;
    }
    public Product toProduct(ProductDto productDto) {
        Product result= new Product();
        result.setName(productDto.getName());
        result.setPrice(productDto.getPrice());
        result.setBrand(productDto.getBrand());
        result.setImage(productDto.getImage());
        result.setDescription(productDto.getDescription());
        result.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        result.setSpecification(productDto.getSpecification());
        result.setStatus(productDto.getStatus());
        return result;
    }
}
