package org.eu.wuname.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.domain.vo.PageVo;
import org.eu.wuname.domain.ResponseResult;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2024-08-03 16:02:39
 */
public interface ArticleService extends IService<Article> {

    ResponseResult<PageVo> articleList(Integer pageNum, Integer pageSize, String keyword);

    ResponseResult addArticle(Article article);

    ResponseResult updateArticle(Article article);

    ResponseResult getPublicArticle(Integer pageNumber, Long categoryId, String keyword);

    ResponseResult getArticleDetail(Long articleId);
}

