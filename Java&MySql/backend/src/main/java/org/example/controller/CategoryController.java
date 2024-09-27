package org.example.controller;

import org.apache.ibatis.annotations.Delete;
import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.pojo.Result;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {   //using list to carry the returned daya because it's a type of array.
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/all")
    public Result<List<Category>> listAll(){
        List<Category> cs=categoryService.listAll();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer category_id){
        Category cg= categoryService.findById(category_id);
        return Result.success(cg);
    }

    @PutMapping()
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer category_id){
        categoryService.delete(category_id);
        return Result.success();
    }
}
