package org.eu.wuname.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.eu.wuname.domain.entity.Menu;
import org.eu.wuname.mapper.MenuMapper;
import org.eu.wuname.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.LoginUser;
import org.eu.wuname.domain.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 35238
 * @date 2023/7/22 0022 21:59
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Override
    //在这里之前，我们已经拿到了登录的用户名和密码。UserDetails是SpringSecurity官方提供的接口
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据拿到的用户名，并结合查询条件(数据库是否有这个用户名)，去查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);

        //判断是否查询到用户，也就是这个用户是否存在，如果没查到就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");//后期会对异常进行统一处理
        }

        List<Menu> menus = menuMapper.selectMenuByUserId(user.getId());

        List<String> collect = menus.stream()
                .map(Menu::getMenuName)
                .collect(Collectors.toList());


        return new LoginUser(user,collect,null);

    }
}
