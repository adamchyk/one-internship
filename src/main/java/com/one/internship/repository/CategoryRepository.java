package com.one.internship.repository;

import com.one.internship.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByOwnerIdAndName(Integer ownerId, String name);

    List<Category> findByOwnerId(Integer id);
}
