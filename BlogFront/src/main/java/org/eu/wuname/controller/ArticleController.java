package org.eu.wuname.controller;

import org.eu.wuname.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.eu.wuname.domain.ResponseResult;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/getPublicArticle")
    public ResponseResult getPublicArticle(Integer pageNumber,Long categoryId,String keyword){
        return articleService.getPublicArticle(pageNumber,categoryId,keyword);
    }

    @GetMapping("/getArticleDetail/{id}")
    public ResponseResult getArticleDetail(@PathVariable Long id){
        return articleService.getArticleDetail(id);
    }

}
