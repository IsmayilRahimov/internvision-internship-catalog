package org.example.internvisioninternshipcatalog.repository;

import org.example.internvisioninternshipcatalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
