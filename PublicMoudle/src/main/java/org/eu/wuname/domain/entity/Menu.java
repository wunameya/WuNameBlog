package org.eu.wuname.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2024-08-02 15:43:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends Model<Menu> {
    //菜单ID
    private Long id;
    //菜单名称
    private String menuName;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;
}