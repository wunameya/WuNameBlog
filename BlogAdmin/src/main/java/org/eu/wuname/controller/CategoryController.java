package org.eu.wuname.controller;

import org.eu.wuname.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/categoryList")
    public ResponseResult categoryList(){
        return ResponseResult.okResult(categoryService.categoryList());
    }
    @PostMapping("/addCategory")
    public ResponseResult addCategory(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @PostMapping("/deleteCategory")
    public ResponseResult deleteCategory(Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }
    @PostMapping("/updateCategory")
    public ResponseResult updateCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }
}
