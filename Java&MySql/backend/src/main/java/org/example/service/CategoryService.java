package org.example.service;

import org.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer categoryId);

    void update(Category category);

    void delete(Integer categoryId);

    List<Category> listAll();
}
