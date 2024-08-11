package org.eu.wuname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.eu.wuname.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-02 15:43:23
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectMenuByUserId(Long id);
}

