package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.entity.BlogInfo;
import org.eu.wuname.domain.ResponseResult;

/**
 * (BlogInfo)表服务接口
 *
 * @author makejava
 * @since 2024-08-06 22:51:22
 */
public interface BlogInfoService extends IService<BlogInfo> {




    ResponseResult getBlogInfo();

    ResponseResult updateBlogInfo(BlogInfo blogInfo);


}

