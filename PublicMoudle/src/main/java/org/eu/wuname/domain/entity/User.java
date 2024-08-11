package org.eu.wuname.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-07-31 22:31:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {
    //主键
    private Long id;
    //用户名
    private String userName;
    //密码
    private String password;

}