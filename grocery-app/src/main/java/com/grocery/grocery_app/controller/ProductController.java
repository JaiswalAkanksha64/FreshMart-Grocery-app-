package com.grocery.grocery_app.controller;

import com.grocery.grocery_app.dto.ApiResponse;
import com.grocery.grocery_app.dto.ProductDTO;
import com.grocery.grocery_app.model.Product;
import com.grocery.grocery_app.service.ProductService;
import com.grocery.grocery_app.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts()
                .stream().map(Mapper::toProductDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Products fetched", products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Product fetched",
                Mapper.toProductDTO(productService.getProductById(id))));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId)
                .stream().map(Mapper::toProductDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Products fetched", products));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> search(@RequestParam String keyword) {
        List<ProductDTO> products = productService.searchProducts(keyword)
                .stream().map(Mapper::toProductDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Search results", products));
    }

    @PostMapping("/admin/add")
    public ResponseEntity<ApiResponse<ProductDTO>> addProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer stock,
            @RequestParam Long categoryId,
            @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(ApiResponse.success("Product added",
                Mapper.toProductDTO(productService.addProduct(
                        name, description, price, stock, categoryId, image))));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer stock,
            @RequestParam Long categoryId,
            @RequestParam(required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(ApiResponse.success("Product updated",
                Mapper.toProductDTO(productService.updateProduct(
                        id, name, description, price, stock, categoryId, image))));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted", null));
    }

}
