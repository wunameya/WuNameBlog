package org.eu.wuname.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (UserRole)表实体类
 *
 * @author makejava
 * @since 2024-12-18 16:49:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role")
public class UserRole {
    //用户id@TableId
    private Long userId;
    //角色id@TableId
    private Long roleId;

    
}
