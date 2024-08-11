package org.eu.wuname.cornjob;


import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.service.ArticleService;
import org.eu.wuname.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCount {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateViewCount(){
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        //让双列集合调用entrySet方法即可转为单列集合，然后才能调用stream方法
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                //把最终数据转为List集合
                .collect(Collectors.toList());
        //把获取到的浏览量更新到mysql数据库中。updateBatchById是mybatisplus提供的批量操作数据的接口
        articleService.updateBatchById(articles);
    }
}
