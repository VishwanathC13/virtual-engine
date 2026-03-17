package com.example.virtualengine.analytics;

import com.example.virtualengine.redis.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class AnalyticsAspect {
    private final RedisService redisService;

    public AnalyticsAspect(RedisService redisService) {
        this.redisService = redisService;
    }

    @AfterReturning("@annotation(trackEvent) && args(payload,..)")
    public void afterTrack(JoinPoint jp, TrackEvent trackEvent, Map<String, Object> payload) {
        // basic processing: increment a counter in Redis or push event
        redisService.increment("analytics:events");
    }
}
