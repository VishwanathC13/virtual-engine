package com.example.virtualengine.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key, 1);
    }

    public Long getLong(String key) {
        Object v = redisTemplate.opsForValue().get(key);
        if (v == null) return null;
        if (v instanceof Long) return (Long) v;
        if (v instanceof Integer) return ((Integer) v).longValue();
        try {
            return Long.parseLong(v.toString());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
