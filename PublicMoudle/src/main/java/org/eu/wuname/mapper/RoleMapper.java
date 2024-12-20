package org.eu.wuname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.eu.wuname.domain.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleByUserId(Long id);
}
