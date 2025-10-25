package org.example.internvisioninternshipcatalog.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.internvisioninternshipcatalog.exceptions.CategoryNotFoundException;

import org.example.internvisioninternshipcatalog.model.Category;
import org.example.internvisioninternshipcatalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category category) {
        Category updatedCategory = categoryRepository.findById(id).get();
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());
        return categoryRepository.save(updatedCategory);
    }

    public String deleteById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return "Deleted category with id: " + id;
                })
                .orElse("Category with id: " + id + " not found");
    }

    @SneakyThrows
    public Category getByid(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));

    }

}
