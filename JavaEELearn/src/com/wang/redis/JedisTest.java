package com.wang.redis;

import redis.clients.jedis.Jedis;

public class JedisTest {
    //通过java程序访问redies
    public static void main(String[] args) {
        //获得连接对象
        Jedis jedis = new Jedis("localhost",6379);

        //获得数据
        String username = jedis.get("username");
        System.out.println(username);
        //存储
        jedis.set("addr","杭州");
        System.out.println(jedis.get("addr"));
        //关闭资源
        jedis.close();
    }
}
