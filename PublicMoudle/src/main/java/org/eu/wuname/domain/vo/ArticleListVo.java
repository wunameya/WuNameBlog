package org.eu.wuname.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleListVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String time;

    @TableField(exist = false)
    private String categoryName;
}
