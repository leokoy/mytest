package com.itheima.demo01;

import com.itheima.utils.JedisUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Demo {
   @Test
    public void testJedisSingle(){
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set("name","itheima");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
   }
    @Test
    public void testPool(){

        String host ="localhost";
        int port = 6379;
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxWaitMillis(3000);


        JedisPool pool = new JedisPool(poolConfig,host,port);

        //2.从连接池中获取连接对象
        Jedis jedis = pool.getResource();

        //3.调用方法存放数据
        jedis.set("nickname","贾克斯");

        //4.将连接归还到连接池
        jedis.close();

    }

    @Test
    public void testJedisUtil(){
        Jedis jedis = JedisUtil.getJedisFromPool();

        String nickname = jedis.get("nickname");

        jedis.close();//这句代码一定要记得
        System.out.println(nickname);
    }

}
