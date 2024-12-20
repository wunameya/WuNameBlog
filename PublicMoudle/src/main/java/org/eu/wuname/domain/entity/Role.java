package org.eu.wuname.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2024-12-18 16:11:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role  {
    //角色id@TableId
    private Long id;

    //角色名称
    private String roleName;
    
}
