package org.eu.wuname.service.impl;



import org.eu.wuname.service.SystemLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.LoginUser;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.User;
import org.eu.wuname.utils.JwtUtil;
import org.eu.wuname.utils.RedisCache;
import org.eu.wuname.utils.SecurityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 35238
 * @date 2023/7/22 0022 21:39
 */
@Service
//认证，判断用户登录是否成功
public class SystemLoginServiceImpl implements SystemLoginService {

    @Autowired
    //AuthenticationManager是security官方提供的接口
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;



    @Override
    public ResponseResult login(User user) {
        //封装登录的用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //在下一行之前，封装的数据会先走UserDetailsServiceImpl实现类，这个实现类在我们的主工程的service/impl目录里面
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //上面那一行会得到所有的认证用户信息authenticate。然后下一行需要判断用户认证是否通过，如果authenticate的值是null，就说明认证没有通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //把这个userid通过我们写的JwtUtil工具类转成密文，这个密文就是token值
        String jwt = JwtUtil.createJWT(userId);

        //下面那行的第一个参数: 把上面那行的jwt，也就是token值保存到Redis。存到时候是键值对的形式，值就是jwt，key要加上 "login:" 前缀
        //下面那行的第二个参数: 要把哪个对象存入Redis。我们写的是loginUser，里面有权限信息，后面会用到
        redisCache.setCacheObject("login:"+userId,loginUser);


        //把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        LoginUser id = SecurityUtils.getLoginUser();
        redisCache.deleteObject("login"+id);
        return ResponseResult.okResult();
    }
}
