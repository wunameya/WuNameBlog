package org.eu.wuname.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.eu.wuname.constants.SystemCanstants;
import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.domain.vo.PageVo;
import org.eu.wuname.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.eu.wuname.constants.AppHttpCodeEnum;
import org.eu.wuname.domain.ResponseResult;
import org.eu.wuname.domain.entity.Category;
import org.eu.wuname.domain.vo.ArticleDetail;
import org.eu.wuname.domain.vo.ArticleListVo;
import org.eu.wuname.service.ArticleService;
import org.eu.wuname.service.CategoryService;
import org.eu.wuname.utils.BeanCopyUtils;
import org.eu.wuname.utils.RedisCache;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2024-08-03 16:02:39
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult<PageVo> articleList(Integer pageNum, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(keyword), Article::getTitle, keyword);

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);

        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    @Transactional
    public ResponseResult addArticle(Article article) {
        if(!StringUtils.hasText(article.getSummary())){
            article.setSummary(article.getContent().substring(0, Math.min(article.getContent().length(), 50)).replaceAll("\\p{Punct}", ""));
        }
        save(article);
        redisCache.setCacheMapValue("article:viewCount", Long.toString(article.getId()), 1);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateArticle(Article article) {
        if(!StringUtils.hasText(article.getSummary())){
            article.setSummary(article.getContent().substring(0, Math.min(article.getContent().length(), 50)).replaceAll("\\p{Punct}", ""));
        }
        updateById(article);
        return ResponseResult.okResult();
    }

    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseResult getPublicArticle(Integer pageNumber, Long categoryId, String keyword) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(keyword), Article::getTitle, keyword);
        queryWrapper.eq(categoryId!=null,Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemCanstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderBy(true, false, Article::getId);

        Page<Article> page = new Page<>();
        page.setCurrent(pageNumber);
        page.setSize(10);
        page(page, queryWrapper);

        List<Category> categoryList = categoryService.list();

        Map<Long, String> categoryMap = categoryList.stream()
                .collect(toMap(Category::getId, Category::getName));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        page.getRecords()
                .forEach(article -> {
                    article.setCategoryName(categoryMap.get(article.getCategoryId()));
                    article.setTime(sdf.format(article.getUpdateTime()));
                });
        List<Article> records = page.getRecords();
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(records, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos ,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult getArticleDetail(Long articleId) {
        Article article = getById(articleId);
        if(article==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NOT_FOUND);
        }
        if(!article.getStatus().equals(SystemCanstants.STATUS_NORMAL)){
            return ResponseResult.errorResult(AppHttpCodeEnum.ARTICLE_DRAFT);
        }
        ArticleDetail articleDetail = BeanCopyUtils.copyBean(article, ArticleDetail.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        articleDetail.setTime(sdf.format(article.getUpdateTime()));

        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", articleId.toString());
        articleDetail.setViewCount(viewCount.longValue());

        redisCache.incrementCacheMapValue("article:viewCount",articleId.toString(),1);
        return ResponseResult.okResult(articleDetail);
    }
}

