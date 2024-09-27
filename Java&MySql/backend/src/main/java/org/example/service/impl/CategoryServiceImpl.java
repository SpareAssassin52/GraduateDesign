package org.example.service.impl;

import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.service.CategoryService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer user_id = (Integer) claims.get("user_id");
        category.setCreated_user(user_id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        // mapper needs the id of whom created category
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("user_id");
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer categoryId) {
        return categoryMapper.findById(categoryId);
    }

    @Override
    public void update(Category category) {
        category.setUpdated_at(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer categoryId) {
        categoryMapper.delete(categoryId);
    }

    @Override
    public List<Category> listAll() {
        return categoryMapper.listAll();
    }

}
