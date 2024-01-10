package com.example.headphonestore.Service;

import com.example.headphonestore.Dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto dto);
    ProductDto updateProduct(Long id, ProductDto dto) throws IOException;

    String deleteProduct(Long id);

    ProductDto createProductTest(ProductDto dto) throws IOException;
    ProductDto updateProductV2(Long id, ProductDto dto) throws IOException;


    byte[] downloadProductImage(String fileName) throws IOException;
}
