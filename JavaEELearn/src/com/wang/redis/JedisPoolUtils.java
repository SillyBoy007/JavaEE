package com.wang.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private  static JedisPool jedisPool=null;
    static {
        //加载配置文件
        InputStream inputStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties pro = new Properties();
        try {
            pro.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获得池子配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxIdle"))); //设置最大闲置个数(超过30个闲置的就关了)
        config.setMinIdle(Integer.parseInt(pro.getProperty("redis.minIdle"))); //最小闲置个数
        config.setMaxTotal(Integer.parseInt(pro.getProperty("redis.maxTotal"))); //最大连接数
        jedisPool = new JedisPool(config,pro.getProperty("redis.url"), Integer.parseInt(pro.getProperty("redis.port")));
    }
    //获得jedies资源
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
    public static void closeJedis(){
        jedisPool.close();
    }
}
