package cn.com.sharinglife.config.cacheenum;

import java.util.concurrent.TimeUnit;

/**
 * 用于配置不同的缓存
 * Created by hell on 2018/2/2
 *
 * @author hell
 */
public enum CachesEnum {

    USER_SERVICE_CACHE("userServiceCache", 2, 500, TimeUnit.SECONDS, 100);

    /**
     * 缓存名称
     */
    private String cacheName;
    /**
     * 缓存策略
     * 1：expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
     * 2：expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
     * 3：refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
     */
    private int strategy;
    /**
     * 缓存时长
     */
    private int Ttl;
    /**
     * 时间单位
     */
    private TimeUnit unit;
    /**
     * 缓存数量
     */
    private Integer maxSize;

    CachesEnum(String cacheName, int strategy, int ttl, TimeUnit unit, Integer maxSize) {
        this.cacheName = cacheName;
        this.strategy = strategy;
        Ttl = ttl;
        this.unit = unit;
        this.maxSize = maxSize;
    }

    public String getCacheName() {
        return cacheName;
    }

    public int getStrategy() {
        return strategy;
    }

    public int getTtl() {
        return Ttl;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public Integer getMaxSize() {
        return maxSize;
    }
}
