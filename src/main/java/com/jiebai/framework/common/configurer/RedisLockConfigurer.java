package com.jiebai.framework.common.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * RedisLockConfigurer
 *
 * @author lizhihui
 * @version 1.0.0
 */
@Configuration
public class RedisLockConfigurer {

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "qqsk-lock");
    }
}
