package cn.com.sharinglife.config;

import cn.com.sharinglife.config.Enum.CachesEnum;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * Caffeine Cached的配置类，spring-boot推荐使用Caffeine Cache
 * 可以针对每个cache配置不同的参数
 *
 * Created by hell on 2018/2/2
 *
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ArrayList<CaffeineCache> caffeineCaches = new ArrayList<>();
        for(CachesEnum c : CachesEnum.values()){
            caffeineCaches.add(new CaffeineCache(c.getCacheName(), getCaffeine(c).build()));
        }
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }

    private Caffeine<Object, Object>  getCaffeine(CachesEnum c){
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().recordStats();
        int strategy = c.getStrategy();
        switch (strategy){
            case 1:
                caffeine.expireAfterAccess(c.getTtl(),c.getUnit());
                break;
            case 2:
                caffeine.expireAfterWrite(c.getTtl(),c.getUnit());
                break;
            case 3:
                caffeine.refreshAfterWrite(c.getTtl(),c.getUnit());
                break;
            default:
                break;
        }
        caffeine.maximumSize(c.getMaxSize());
        return caffeine;
    }
}
