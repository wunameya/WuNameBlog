package org.eu.wuname.controller;

import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.BlogInfo;
import org.eu.wuname.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogInfo")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;



    @GetMapping("/getBlogInfo")
    public ResponseResult getBlogInfo() {
        return blogInfoService.getBlogInfo();
    }

    @PostMapping("/updateBlogInfo")
    public ResponseResult updateBlogInfo(@RequestBody BlogInfo blogInfo) {
        return blogInfoService.updateBlogInfo(blogInfo);
    }


}
