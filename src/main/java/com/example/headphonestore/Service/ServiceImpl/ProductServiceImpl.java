package com.example.headphonestore.Service.ServiceImpl;

import com.example.headphonestore.Convert.ProductConvert;
import com.example.headphonestore.Dto.ProductDto;
import com.example.headphonestore.Entity.Product;
import com.example.headphonestore.Repository.CategoryRepository;
import com.example.headphonestore.Repository.ProductRepository;
import com.example.headphonestore.Service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources");

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConvert productConvert;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductConvert productConvert) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productConvert = productConvert;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> new ProductDto(
                product.getId(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                product.getBrand(),
                product.getSpecification(),
                product.getStatus(),null)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        Product product = productConvert.toProduct(dto);
        Product save = productRepository.save(product);
        return productConvert.toProductDTO(save);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto dto) throws IOException {
        Product product = productRepository.getProductById(id);
        if (!Objects.equals(dto.getImageFile().getOriginalFilename(), "")){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(dto.getImageFile().getOriginalFilename()));
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("product-images");
            Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = dto.getImageFile().getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            product.setImage(dto.getImageFile().getOriginalFilename());

        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
        product.setSpecification(dto.getSpecification());
        product.setStatus(dto.getStatus());
        Product save = productRepository.save(product);
        return productConvert.toProductDTO(save);
    }

    @Override
    @Transactional
    public String deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return "Delete";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail delete";
    }

    @Override
    public ProductDto createProductTest(ProductDto dto) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(dto.getImageFile().getOriginalFilename()));
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("product-images");
        Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = dto.getImageFile().getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setBrand(dto.getBrand());
        product.setImage(dto.getImageFile().getOriginalFilename());
        product.setDescription(dto.getDescription());
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
        product.setSpecification(dto.getSpecification());
        product.setStatus(dto.getStatus());
        Product save = productRepository.save(product);
        return productConvert.toProductDTO(save);
    }

    @Override
    public ProductDto updateProductV2(Long id, ProductDto dto) throws IOException {
        Product product = productRepository.getProductById(id);
        if (!Objects.equals(dto.getImageFile(), null)){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(dto.getImageFile().getOriginalFilename()));
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("product-images");
            Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = dto.getImageFile().getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            product.setImage(dto.getImageFile().getOriginalFilename());

        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).get());
        product.setSpecification(dto.getSpecification());
        product.setStatus(dto.getStatus());
        Product save = productRepository.save(product);
        return productConvert.toProductDTO(save);
    }

    @Override
    public byte[] downloadProductImage(String fileName) throws IOException {
        Product product= productRepository.getProductByImageLike(fileName);
        String filePath = System.getProperty("user.dir")+"/src/main/resources/static/product-images/" + product.getImage();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
