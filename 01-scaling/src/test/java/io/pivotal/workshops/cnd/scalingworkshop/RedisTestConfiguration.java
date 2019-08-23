package io.pivotal.workshops.cnd.scalingworkshop;

import javax.annotation.PreDestroy;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import redis.embedded.RedisServer;

/**
 * RedisConfiguration
 */
@TestConfiguration
@EnableRedisRepositories
public class RedisTestConfiguration {

    private final RedisServer redisServer;

    public RedisTestConfiguration() {
        RedisServer redisServer = new RedisServer(6379);
        redisServer.start();
        this.redisServer = redisServer;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }
}