package com.yyh.util;

import redis.clients.jedis.Jedis;

/**
 * @author yihui.yan
 * @date 2019/11/17/027
 */
public class RedisCacheUtil {
    /**
     * 连接本地的 Redis 服务
     */
    private static Jedis jedis = new Jedis("localhost");

    /**
     * 已解析url缓存
     */
    public static synchronized void urlCache(String urlId, String url) {
        //存储数据到set中
        jedis.sadd(urlId, url);
    }

    /**
     * 查看url是否已存在缓存中
     * @return true 存在 false不存在
     */
    public static synchronized boolean urlCheck(String url) {
        return jedis.sismember(MD5Util.MD5(url),url);
    }
}
