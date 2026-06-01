package com.grocery.grocery_app.service;

import com.grocery.grocery_app.model.Category;
import com.grocery.grocery_app.model.Product;
import com.grocery.grocery_app.repository.CategoryRepository;
import com.grocery.grocery_app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final String UPLOAD_DIR = "uploads/products/";

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Product addProduct(String name, String description,
                              Double price, Integer stock,
                              Long categoryId, MultipartFile image) throws IOException {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        String imageUrl = saveImage(image);

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stock);
        product.setCategory(category);
        product.setImageUrl(imageUrl);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String description,
                                 Double price, Integer stock,
                                 Long categoryId, MultipartFile image) throws IOException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stock);
        product.setCategory(category);

        if (image != null && !image.isEmpty()) {
            product.setImageUrl(saveImage(image));
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + filename);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return "/" + UPLOAD_DIR + filename;
    }

}
