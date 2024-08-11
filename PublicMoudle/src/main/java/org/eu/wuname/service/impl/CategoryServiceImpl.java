package org.eu.wuname.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.entity.Category;
import org.eu.wuname.mapper.CategoryMapper;
import org.eu.wuname.service.CategoryService;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-08-03 10:35:38
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> categoryList() {
        List<Category> list = list();
        return list;
    }
}

