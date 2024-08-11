package org.eu.wuname.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * (BlogInfo)表实体类
 *
 * @author makejava
 * @since 2024-08-07 10:36:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo extends Model<BlogInfo> {

    private Long id;
    //右上角的字段
    private String blogName;
    //博客信息的html
    private String blogInfoHtml;
    //博客的footer
    private String blogFooter;
    //博客的ico图片地址
    private String blogIcoUrl;
    //博客的title
    private String blogTitle;



}

