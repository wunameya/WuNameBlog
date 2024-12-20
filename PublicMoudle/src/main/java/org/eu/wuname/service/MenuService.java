package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.entity.Menu;
import org.eu.wuname.domain.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2024-08-02 15:43:25
 */

public interface MenuService extends IService<Menu> {

    ResponseResult getMenu();
}

