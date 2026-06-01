package com.grocery.grocery_app.controller;

import com.grocery.grocery_app.dto.ApiResponse;
import com.grocery.grocery_app.model.Category;
import com.grocery.grocery_app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success("Categories fetched",
                categoryService.getAllCategories()));
    }

    @PostMapping("/admin/add")
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(ApiResponse.success("Category added",
                categoryService.addCategory(category)));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(ApiResponse.success("Category updated",
                categoryService.updateCategory(id, category)));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted", null));
    }

}
