package com.one.internship.controller;

import com.one.internship.entity.Category;
import com.one.internship.entity.User;
import com.one.internship.model.CategoryInfo;
import com.one.internship.repository.CategoryRepository;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/categories")
    public List<CategoryInfo> getCategories(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findByOwnerId(user.getId());
        for (int i = 0; i < categoryList.size(); i++) {
            CategoryInfo categoryInfo = new CategoryInfo();
            categoryInfo.setId(categoryList.get(i).getCategoryId());
            categoryInfo.setName(categoryList.get(i).getName());
            categoryInfos.add(categoryInfo);
        }
        return categoryInfos;
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable("id") Integer noteId) {
        categoryRepository.deleteById(noteId);
    }

}
