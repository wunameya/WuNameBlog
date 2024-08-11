package org.eu.wuname.controller;

import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategory")
    public ResponseResult getCategory(){
        return ResponseResult.okResult(categoryService.list());
    }
}
