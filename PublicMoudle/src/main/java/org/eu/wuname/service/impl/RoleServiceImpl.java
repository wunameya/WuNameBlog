package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.Role;
import org.eu.wuname.mapper.RoleMapper;
import org.eu.wuname.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2024-12-18 16:11:13
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public ResponseResult getAllRole() {
        return ResponseResult.okResult(list());
    }
}
