package org.eu.wuname.controller;

import org.eu.wuname.domain.dto.AddUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.service.MenuService;
import org.eu.wuname.service.UserService;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/addUser")
    public ResponseResult addUser(@RequestBody AddUserDto user){
        return userService.addUser(user);
    }

    @GetMapping("/userList")
    public ResponseResult userList(Integer pageNum, Integer pageSize,String username){
        return userService.userList(pageNum,pageSize,username);
    }

    @PostMapping("/deleteUser")
    public ResponseResult deleteUser(Long id){
        userService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/getAllMenus")
    public ResponseResult getAllMenus(){
        return menuService.getAllMenus();
    }

}
