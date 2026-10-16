package org.eu.wuname.runner;


import org.eu.wuname.domain.entity.Article;
import org.eu.wuname.mapper.ArticleMapper;
import org.eu.wuname.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    private static final String ARTICLE_VIEW_COUNT_KEY = "article:viewCount";

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Object> cachedViewCountMap = redisCache.getCacheMap(ARTICLE_VIEW_COUNT_KEY);
        final Map<String, Object> existingViewCountMap =
                cachedViewCountMap == null ? Collections.emptyMap() : cachedViewCountMap;
        Map<String, Long> viewCountMap = articles.stream()
                .collect(Collectors.toMap(
                        article -> article.getId().toString(),
                        article -> Math.max(
                                toLong(article.getViewCount(), 0L),
                                toLong(existingViewCountMap.get(article.getId().toString()), 0L)
                        )
                ));
        redisCache.setCacheMap(ARTICLE_VIEW_COUNT_KEY, viewCountMap);
    }

    private Long toLong(Object value, Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
