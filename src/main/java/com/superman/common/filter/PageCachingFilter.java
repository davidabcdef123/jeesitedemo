package com.superman.common.filter;

import com.superman.common.utils.CacheUtils;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

/**
 *  页面高速缓存过滤器
 * Created by Super on 2016/7/12.
 */
public class PageCachingFilter extends SimplePageCachingFilter{
        @Override
        protected CacheManager getCacheManager() {
            return CacheUtils.getCacheManager();
        }
}
