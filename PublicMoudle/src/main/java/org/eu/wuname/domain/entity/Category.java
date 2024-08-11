package org.eu.wuname.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类表(Category)表实体类
 *
 * @author makejava
 * @since 2024-08-03 10:35:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends Model<Category> {

    private Long id;
    //分类名
    private String name;
    //描述
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}