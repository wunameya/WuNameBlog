package org.eu.wuname.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail {
    private Long id;

    private String title;

    private String content;

    private Date updateTime;


    private String time;

    //访问量
    private Long viewCount;
}
