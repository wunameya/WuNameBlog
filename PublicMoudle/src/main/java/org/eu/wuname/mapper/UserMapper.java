package org.eu.wuname.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.eu.wuname.domain.entity.User;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-04 11:22:36
 */
public interface UserMapper extends BaseMapper<User> {
    public User getUser(Integer id);
}

