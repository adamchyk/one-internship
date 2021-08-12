package com.one.internship.controller;

import com.one.internship.entity.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CategoryController {

    List<Category> categoriesList = new ArrayList<>();

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoriesList;
    }

    @PostMapping("/categories")
    public void addCategories(@RequestBody Category category){
        category.setCategoryId((Integer) (categoriesList.size() + 1));
        categoriesList.add(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteUser(@PathVariable("id") Integer categoryId) {
        Iterator<Category> categoriesList = getCategories().listIterator();
        while (categoriesList.hasNext()) {
            Category category = categoriesList.next();
            if (category.getCategoryId().equals(categoryId)) {
                categoriesList.remove();
                break;
            }
        }
    }

}
