package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.eu.wuname.mapper.UserMenuMapper;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.entity.UserMenu;
import org.eu.wuname.service.UserMenuService;

@Service()
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper,UserMenu> implements UserMenuService {
}
