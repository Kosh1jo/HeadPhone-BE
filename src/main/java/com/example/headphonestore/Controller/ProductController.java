package com.example.headphonestore.Controller;

import com.example.headphonestore.Dto.BaseResponse;
import com.example.headphonestore.Dto.ProductDto;
import com.example.headphonestore.Service.ProductService;
import com.example.headphonestore.Shared.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllProducts(){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.getAllProducts(),"Get all product complete"));
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createProduct(@RequestBody ProductDto dto){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.createProduct(dto),"Create product complete"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateProduct(@PathVariable(value = "id") Long id,@RequestBody ProductDto dto) throws IOException {
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.updateProduct(id,dto),"update product complete"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.deleteProduct(id),"Delete product complete"));
    }
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = productService.downloadProductImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
    @PostMapping("/createTest")
    public ResponseEntity<BaseResponse> createProductModel(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam String brand,
            @RequestParam Long categoryId,
            @RequestParam String description,
            @RequestParam String specification,
            @RequestParam Status status,
            @RequestPart MultipartFile imageFile) throws IOException {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setBrand(brand);
        productDto.setImageFile(imageFile);
        productDto.setDescription(description);
        productDto.setCategoryId(categoryId);
        productDto.setSpecification(specification);
        productDto.setStatus(status);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.createProductTest(productDto),"Create product complete"));
    }
    @PutMapping("/updateTest/{id}")
    public ResponseEntity<BaseResponse> updateProductV2(@PathVariable(value = "id") Long id,
                                                        @RequestParam String name,
                                                        @RequestParam double price,
                                                        @RequestParam String brand,
                                                        @RequestParam Long categoryId,
                                                        @RequestParam String description,
                                                        @RequestParam String specification,
                                                        @RequestParam Status status,
                                                        @RequestPart(required = false) MultipartFile imageFile) throws IOException {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setBrand(brand);
        productDto.setImageFile(imageFile);
        productDto.setDescription(description);
        productDto.setCategoryId(categoryId);
        productDto.setSpecification(specification);
        productDto.setStatus(status);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), productService.updateProductV2(id,productDto),"update product complete"));
    }
}
