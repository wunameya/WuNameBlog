package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.entity.Category;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-08-03 10:35:38
 */
public interface CategoryService extends IService<Category> {

    List<Category> categoryList();
}

