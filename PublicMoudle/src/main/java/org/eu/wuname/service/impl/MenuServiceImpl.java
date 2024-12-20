package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.eu.wuname.domain.entity.Menu;
import org.eu.wuname.mapper.MenuMapper;
import org.eu.wuname.service.MenuService;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-08-02 15:43:26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public ResponseResult getMenu() {
        Long userId = SecurityUtils.getUserId();
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = menuMapper.selectMenuByUserId(userId);

        menus.add(new Menu(101l,"修改密码",1,"/changePassword"));
        menus.add(new Menu(100l,"退出登录",1,"/logout"));
        return ResponseResult.okResult(menus);
    }

    
}

