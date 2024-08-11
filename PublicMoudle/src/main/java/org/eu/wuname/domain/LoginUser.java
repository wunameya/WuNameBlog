package org.eu.wuname.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.wuname.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 35238
 * @date 2023/7/22 0022 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//UserDetails是SpringSecurity官方提供的接口
public class LoginUser implements UserDetails {

    private User user;

    //权限信息的集合
    private List<String> permissions;

    @JSONField(serialize = false) //这个注解的作用是不让下面那行的成员变量序列化存入redis，避免redis不支持而报异常
    private List<SimpleGrantedAuthority> authorities;

    @Override
    //用于返回权限信息。现在我们正在实现'认证'，'权限'后面才用得到。所以返回null即可
    //当要查询用户信息的时候，我们不能单纯返回null，要重写这个方法，作用是返回权限信息
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){ //严谨来说这个if判断是避免整个调用链中security本地线程变量在获取用户时的重复解析，和redis存取无关
            return authorities;
        }
        //为空的话就会执行下面的转换代码
        //List<SimpleGrantedAuthority> authorities = xxpermissions //简化这行如下一行，我们把authorities成员变量写到外面了
        authorities = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //最终返回转换结果
        return authorities;
    }

    @Override
    //用于获取用户密码。由于使用的实体类是User，所以获取的是数据库的用户密码
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    //用于获取用户名。由于使用的实体类是User，所以获取的是数据库的用户名
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    //判断登录状态是否过期。把这个改成true，表示永不过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //判断账号是否被锁定。把这个改成true，表示未锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //判断登录凭证是否过期。把这个改成true，表示永不过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //判断用户是否可用。把这个改成true，表示可用状态
    public boolean isEnabled() {
        return true;
    }
}
