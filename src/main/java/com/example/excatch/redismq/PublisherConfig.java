package com.example.excatch.redismq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author songqd
 * @Date 2019/9/6
 * @Description 注册RedisMq，作为消息发布者
 */
@Configuration
public class PublisherConfig {
    @Bean
    public StringRedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
