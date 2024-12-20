package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.Role;


/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2024-12-18 16:11:12
 */
public interface RoleService extends IService<Role> {



    ResponseResult getAllRole();


}
