package org.example.internvisioninternshipcatalog.controller;

import lombok.RequiredArgsConstructor;
import org.example.internvisioninternshipcatalog.model.Product;
import org.example.internvisioninternshipcatalog.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public List<Product> getAll(@RequestParam(required = false) Long categoryId,
                                @RequestParam(required = false) Boolean available) {
        if (categoryId != null) {
            return productService.getByCategory(categoryId);
        } else if (available != null && available) {
            return productService.getAvailableProducts();
        } else {
            return productService.getAll();
        }
    }

    @PostMapping("/create/products")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    @GetMapping("/paged")
    public Page<Product> getPage(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        return productService.getAllPaged(page, size);

    }
}