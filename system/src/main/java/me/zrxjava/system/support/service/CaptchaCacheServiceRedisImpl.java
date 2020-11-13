package me.zrxjava.system.support.service;

import com.anji.captcha.service.CaptchaCacheService;
import me.zrxjava.common.component.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author void
 * @create 2020-11-08
 */
@Service
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        redisUtil.setCacheObject(key, value, (int) expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return redisUtil.exitKey(key);
    }

    @Override
    public void delete(String key) {
        redisUtil.deleteObject(key);
    }

    @Override
    public String get(String key) {
        return redisUtil.getCacheObject(key);
    }

    @Override
    public String type() {
        return  "redis";
    }
}
