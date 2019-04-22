package com.wang.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 通过jedies的pool获得连接对象
 */
public class JedisPoolTest {
    public static void main(String[] args) {
        //创建池子的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(30); //设置最大闲置个数(超过30个闲置的就关了)
        config.setMinIdle(10); //最小闲置个数
        config.setMaxTotal(50); //最大连接数
        //创建一个池子
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        //从池子获取资源
        Jedis jedis = jedisPool.getResource();

        //操作数据
        jedis.set("age","23");
        System.out.println(jedis.get("age"));


        Jedis jedis1 = JedisPoolUtils.getJedis();
        jedis1.set("birthday","1995-06-10");
        //关闭资源
        System.out.println(jedis1.get("birthday"));
        jedis.close();
        jedisPool.close();


    }
}
