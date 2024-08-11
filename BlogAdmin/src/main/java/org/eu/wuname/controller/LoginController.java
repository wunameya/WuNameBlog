package org.eu.wuname.controller;

import org.eu.wuname.service.SystemLoginService;
import org.eu.wuname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.dto.ChangePasswordDto;
import org.eu.wuname.domain.entity.User;

@RestController
public class LoginController {
    @Autowired
    SystemLoginService systemLoginService;

    @Autowired
    UserService userService;



    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return systemLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return systemLoginService.logout();
    }

    @PostMapping("/changePassword")
    public ResponseResult changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return userService.changePassword(changePasswordDto);
    }

}
