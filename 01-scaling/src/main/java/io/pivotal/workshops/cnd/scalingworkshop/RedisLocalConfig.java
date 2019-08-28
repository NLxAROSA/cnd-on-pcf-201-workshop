package io.pivotal.workshops.cnd.scalingworkshop;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.embedded.RedisServer;

@Configuration()
@Profile("local")
public class RedisLocalConfig   {

    private final RedisServer redisServer;

    public RedisLocalConfig() {
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