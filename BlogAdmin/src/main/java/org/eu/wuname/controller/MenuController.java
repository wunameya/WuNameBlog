package org.eu.wuname.controller;

import org.eu.wuname.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.eu.wuname.domain.ResponseResult;

@RestController("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/getMenus")
    public ResponseResult getMenu(){
        return menuService.getMenu();
    }

}
