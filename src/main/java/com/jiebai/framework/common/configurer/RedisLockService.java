package com.jiebai.framework.common.configurer;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author lilin
 * @version 1.0.0
 * redis分布式锁
 */
@Service
public class RedisLockService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 加锁
     *
     * @param key         key
     * @param value       value
     * @param millisecond millisecond
     * @param tryCount    tryCount
     * @return 状态
     */
    public boolean lock(String key, String value, int millisecond, int tryCount) {
        // 重试次数最大5次
        if (tryCount > 5) {
            tryCount = 5;
        }
        try {
            String script = "local key = KEYS[1]; local value = ARGV[1]; if redis.call('set', key, value, 'NX' ,'PX', "
                + millisecond + ") then return 1 else return 0 end";
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Long result =
                redisTemplate.execute(redisScript, Collections.singletonList(key), Collections.singletonList(value));
            // 判断结果
            if (result != null && result == 1) {
                return true;
            }
        } catch (Exception ex) {
            // redis命中失败或连接有问题时，重试机制
            // 重试次数
            if (tryCount == 0) {
                return false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }
            System.out.println(tryCount);
            // 重试机制
            return lock(key, value, millisecond, tryCount - 1);
        }
        return false;
    }

    /**
     * 阻塞锁
     *
     * @param key         key
     * @param value       value
     * @param millisecond millisecond
     * @return 状态
     * @throws InterruptedException InterruptedException
     */
    public boolean blockLock(String key, String value, int millisecond) throws InterruptedException {
        // 被阻塞的时间超过5秒就停止获取锁
        int blockTime = 5000;
        // 默认的间隔时间
        int defaultTime = 1000;
        for (; ; ) {
            if (blockTime >= 0) {
                String script =
                    "local key = KEYS[1]; local value = ARGV[1]; if redis.call('set', key, value, 'NX' ,'PX', "
                        + millisecond + ") then return 1 else return 0 end";
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, long.class);
                Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
                System.out.println("try lock ... ,result: " + result);
                if (result != null && result == 1) {
                    // 得到了锁
                    return true;
                } else {
                    blockTime -= defaultTime;
                    Thread.sleep(1000);
                }
            } else {
                // 已经超时
                return false;
            }
        }
    }

    /**
     * 解锁
     *
     * @param key key
     * @return 状态
     */
    public boolean unlock(String key) {
        return redisTemplate.delete(key);
    }
}