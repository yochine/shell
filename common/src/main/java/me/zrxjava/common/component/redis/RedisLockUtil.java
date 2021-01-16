package me.zrxjava.common.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁
 * @author void
 * @create 2020-11-04
 */
@Component
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;


    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


    /**
     *加锁
     * @param key   加锁键
     * @param value  加锁客户端唯一标识(采用UUID)
     * @param expireTime   锁过期时间
     * @return
     */
    public boolean tryLock(String key, String value, long expireTime) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            /**
             * key 锁，唯一
             * value 分布式锁要满足 解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。
             * SET_IF_NOT_EXIST  当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
             * SET_WITH_EXPIRE_TIME  过期时间单位, EX秒，PX毫秒
             * expireTime 过期时间
             * 设置key 和 有效期 是两步操作  放在这里能保证原子性 不会因为某一步失败发生死锁
             */
            SetParams params = new SetParams();
            params.px(expireTime);
            params.nx();
            String result = jedis.set(key, value, params);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        });
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public boolean unLock(String key, String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            /**
             * RELEASE_LOCK_SCRIPT lua脚本 保证原子性 eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
             */
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key),
                    Collections.singletonList(value));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        });
    }

    /**
     * 尝试获取
     * @param key
     * @return
     */
    public Object tryAcquire(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 续期
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean tryRenew(String key , long time, TimeUnit timeUnit){
        return redisTemplate.expire(key, time, timeUnit);
    }

}
