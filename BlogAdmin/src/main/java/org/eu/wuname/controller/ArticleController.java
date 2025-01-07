package org.eu.wuname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.domain.vo.PageVo;
import org.eu.wuname.service.ArticleService;

import javax.websocket.server.PathParam;

@RestController()
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/articleList")
    public ResponseResult<PageVo> articleList( Integer pageNum, Integer pageSize, String title){
        return articleService.articleList(pageNum,pageSize,title);
    }

    @PostMapping ("/addArticle")
    public ResponseResult addArticle(@RequestBody Article article){
        return articleService.addArticle(article);
    }
    @PostMapping("/updateArticle")
    public ResponseResult updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }
    @PostMapping("/deleteArticle")
    public ResponseResult deleteArticle(Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
