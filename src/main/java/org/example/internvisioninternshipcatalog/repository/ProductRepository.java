package org.example.internvisioninternshipcatalog.repository;

import org.example.internvisioninternshipcatalog.model.Category;
import org.example.internvisioninternshipcatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findByStockGreaterThan(int stock);
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    List<Product> availableByCategory(Category category);


}
