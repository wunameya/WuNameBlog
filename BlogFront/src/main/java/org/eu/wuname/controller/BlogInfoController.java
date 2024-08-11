package org.eu.wuname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.service.BlogInfoService;



@RestController
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;

    @GetMapping("/getBlogInfo")
    private ResponseResult getBlogInfo() {
        return blogInfoService.getBlogInfo();
    }
}
