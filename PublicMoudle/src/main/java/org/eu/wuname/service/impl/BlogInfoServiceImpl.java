package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.eu.wuname.domain.entity.BlogInfo;
import org.eu.wuname.mapper.BlogInfoMapper;
import org.eu.wuname.service.BlogInfoService;
import org.springframework.stereotype.Service;
import org.eu.wuname.domain.ResponseResult;

@Service("blogInfoService")
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements BlogInfoService {

    @Override
    public ResponseResult getBlogInfo() {
        BlogInfo blogInfo = getById(1L);
        return ResponseResult.okResult(blogInfo);
    }

    @Override
    public ResponseResult updateBlogInfo(BlogInfo blogInfo) {
        updateById(blogInfo);
        return ResponseResult.okResult();
    }


}
