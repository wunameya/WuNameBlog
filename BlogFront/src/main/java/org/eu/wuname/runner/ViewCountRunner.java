package org.eu.wuname.runner;


import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.mapper.ArticleMapper;
import org.eu.wuname.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String,Integer> viewCountMap= articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}
