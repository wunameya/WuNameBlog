package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.dto.AddUserDto;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.dto.ChangePasswordDto;
import org.eu.wuname.domain.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-08-04 11:22:40
 */
public interface UserService extends IService<User> {

    ResponseResult addUser(AddUserDto user);

    ResponseResult userList(Integer pageNum, Integer pageSize, String username);

    ResponseResult changePassword(ChangePasswordDto changePasswordDto);
}

