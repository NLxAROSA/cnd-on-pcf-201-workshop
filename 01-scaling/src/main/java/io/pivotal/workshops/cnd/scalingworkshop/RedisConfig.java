package io.pivotal.workshops.cnd.scalingworkshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

@Configuration
class RedisConfig {
    @Bean
    public RedisAtomicLong redisAccessLogCounter(RedisConnectionFactory connFactory) {
        return new RedisAtomicLong("workshop.request.counter", connFactory);
    }
}