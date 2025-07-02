package util;

import redis.clients.jedis.JedisPooled;

public class RedisUtil {
    private static final JedisPooled JEDIS_POOLED = new JedisPooled("localhost", 6380);

    // 기본 10분
    public static void set(String key, String value) {
        set(key, value, 600);
    }

    public static void set(String key, String value, int expiry) {
        JEDIS_POOLED.setex(key, expiry, value);
    }
    
    //키 가져오기
    public static String get(String key) {
        return JEDIS_POOLED.get(key);
    }

    //남은시간
    public static Long ttl(String key) {
        return JEDIS_POOLED.ttl(key);
    }
    public static void remove(String key) {
    	JEDIS_POOLED.del(key);
    }
    //존재여부
    public static boolean exists(String key) {
        return JEDIS_POOLED.exists(key);
    }
}