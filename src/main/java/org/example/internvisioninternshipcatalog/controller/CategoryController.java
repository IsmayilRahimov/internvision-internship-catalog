package org.example.internvisioninternshipcatalog.controller;

import lombok.RequiredArgsConstructor;
import org.example.internvisioninternshipcatalog.model.Category;
import org.example.internvisioninternshipcatalog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping("/getById")
    public Category getById(@PathVariable Long id) {
        return categoryService.getByid(id);
    }

    @PutMapping("/update")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/delete")
    public String deleteById(@PathVariable Long id) {
        return categoryService.deleteById(id);
    }


}
