package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.eu.wuname.constants.SystemCanstants;
import org.eu.wuname.domain.dto.AddUserDto;

import org.eu.wuname.domain.entity.UserRole;
import org.eu.wuname.domain.vo.UserVo;
import org.eu.wuname.mapper.RoleMapper;
import org.eu.wuname.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.eu.wuname.constants.AppHttpCodeEnum;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.dto.ChangePasswordDto;
import org.eu.wuname.domain.entity.User;
import org.eu.wuname.domain.vo.PageVo;
import org.eu.wuname.mapper.MenuMapper;
import org.eu.wuname.mapper.UserMapper;
import org.eu.wuname.service.UserService;
import org.springframework.stereotype.Service;
import org.eu.wuname.utils.BeanCopyUtils;
import org.eu.wuname.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-08-04 11:22:41
 */
@Service()
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public ResponseResult addUser(AddUserDto addUserDto) {
        if(!StringUtils.hasText(addUserDto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(StringUtils.hasText(addUserDto.getUserName())){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserName,addUserDto.getUserName());
            User user = getOne(queryWrapper);
            if(user != null&& addUserDto.getId()==null){
                return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
            }
        }else{
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        User user = BeanCopyUtils.copyBean(addUserDto, User.class);
        if(addUserDto.getId()!=null){
            if(addUserDto.getPassword().equals(SystemCanstants.WONDERFUL_PASSWORD)){
                user.setPassword( getById(user.getId()).getPassword());
            }
            else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            updateById(user);
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            save(user);
        }




        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, user.getId());
        userRoleService.remove(queryWrapper);

        List<UserRole> userRoles = addUserDto.getRoles().stream()
                        .map(roleId -> new UserRole(user.getId(), roleId))
                                .collect(Collectors.toList());


        userRoleService.saveBatch(userRoles);

        return ResponseResult.okResult();
    }

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public ResponseResult userList(Integer pageNum, Integer pageSize, String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(username), User::getUserName, username);

        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<User> userList = page.getRecords();
        List<UserVo> userVos = BeanCopyUtils.copyBeanList(userList, UserVo.class);

        List<UserVo> userVosWithMenus = userVos.stream()
                .map(userVo -> {
                    userVo.setRole(roleMapper.selectRoleByUserId(userVo.getId()));
                    return userVo;
                })
                .collect(Collectors.toList());

        PageVo pageVo = new PageVo(userVosWithMenus, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changePassword(ChangePasswordDto changePasswordDto) {
        if(!StringUtils.hasText(changePasswordDto.getNewPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        Long id = SecurityUtils.getUserId();
        User user = getById(id);

        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(),user.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.OLD_PASSWORD_ERROR);
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        updateById(user);
        return ResponseResult.okResult();
    }
}

