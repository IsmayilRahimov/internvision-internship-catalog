package org.example.internvisioninternshipcatalog.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.internvisioninternshipcatalog.exceptions.ProductNotFoundException;
import org.example.internvisioninternshipcatalog.model.Category;
import org.example.internvisioninternshipcatalog.model.Product;
import org.example.internvisioninternshipcatalog.repository.CategoryRepository;
import org.example.internvisioninternshipcatalog.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category with id " + product.getCategory().getId() + " not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setCategory(product.getCategory());

        if (product.getCategory() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category with id " + product.getCategory().getId() + " not found"));
            existing.setCategory(category);
        }
        return productRepository.save(existing);
    }

    public String deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
        return "Product with id " + id + " has been deleted";
    }

    @SneakyThrows
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public List<Product> getByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));
        return productRepository.findByCategory(category);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByStockGreaterThan(0);
    }

    public Page<Product> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);

    }


}
