package io.pivotal.workshops.cnd.scalingworkshop;

import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

@Component
public class RequestCounter {

    public RequestCounter(RedisAtomicLong redisRequestCounter)  {
        this.redisRequestCounter = redisRequestCounter;
    }

    private final RedisAtomicLong redisRequestCounter;

    public void incrementCounter() {
        redisRequestCounter.incrementAndGet();
    }

    public long getCounter() {
        return redisRequestCounter.get();
    }

    public void resetCounter() {
        redisRequestCounter.set(0L);
    }
}
