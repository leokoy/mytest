package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 包名:com.itheima.utils
 * 作者:Leevi
 * 日期2019-03-21  18:07
 */
public class JedisUtil {
    private static String host;
    private static int port;
    private static int maxIdle;
    private static int maxTotal;
    private static long maxWaitMillis;
    private static JedisPool pool;

    static {
        //从配置文件中读取数据
        ResourceBundle bundle = ResourceBundle.getBundle("jedis");
        host = bundle.getString("host");
        port = Integer.parseInt(bundle.getString("port"));
        maxIdle = Integer.parseInt(bundle.getString("maxIdle"));
        maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
        maxWaitMillis = Long.parseLong(bundle.getString("maxWaitMillis"));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMaxIdle(maxIdle);
        pool = new JedisPool(poolConfig,host,port);
    }

    public static Jedis getJedisFromPool(){
        return pool.getResource();
    }
}
