package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.eu.wuname.domain.entity.UserRole;
import org.eu.wuname.mapper.UserRoleMapper;
import org.eu.wuname.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-12-18 16:49:56
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
