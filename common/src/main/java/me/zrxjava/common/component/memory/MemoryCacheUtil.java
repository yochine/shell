package me.zrxjava.common.component.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 内存缓存
 * @author void
 */
@Component
public final class MemoryCacheUtil {
    private static final Logger logger = LoggerFactory.getLogger(MemoryCacheUtil.class);

    private static final Map<String, Object> CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * 缓存最大个数
     */
    private static Integer CACHE_MAX_NUMBER = 1000;

    /**
     * 初始化
     * @param cacheMaxNumber 缓存最大个数
     * @param second 定时任务 秒执行清除过期缓存
     */
    public static void init(int cacheMaxNumber, long second) {
        CACHE_MAX_NUMBER = cacheMaxNumber;
        if (second > 0L) {
            ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1);
            scheduled.scheduleAtFixedRate(MemoryCacheUtil::refresh, 0, second * 1000, TimeUnit.SECONDS);
        }
    }


    /**
     * 缓存刷新,清除过期数据
     */
    public static void refresh(){
        logger.info("local缓存刷新,清除过期数据");
        for (String key : CACHE_MAP.keySet()) {
            exists(key);
        }
    }


    public static void set(String key, String value, long expiresInSeconds){
        //设置阈值，达到即clear缓存
        if (CACHE_MAP.size() > CACHE_MAX_NUMBER * 2) {
            logger.info("CACHE_MAP达到阈值，clear map");
            clear();
        }
        CACHE_MAP.put(key, value);
        //缓存失效时间
        CACHE_MAP.put(key + "_HoldTime", System.currentTimeMillis() + expiresInSeconds*1000);

    }

    public static void delete(String key){
        CACHE_MAP.remove(key);
        CACHE_MAP.remove(key + "_HoldTime");
    }

    public static boolean exists(String key){
        Long cacheHoldTime = (Long) CACHE_MAP.get(key + "_HoldTime");
        if (cacheHoldTime == null || cacheHoldTime == 0L) {
            return false;
        }
        if (cacheHoldTime < System.currentTimeMillis()) {
            delete(key);
            return false;
        }
        return true;
    }


    public static String get(String key){
        if (exists(key)) {
            return (String)CACHE_MAP.get(key);
        }
        return null;
    }

    /**
     * 删除所有缓存
     */
    public static void clear() {
        logger.info("have clean all key !");
        CACHE_MAP.clear();
    }
}
