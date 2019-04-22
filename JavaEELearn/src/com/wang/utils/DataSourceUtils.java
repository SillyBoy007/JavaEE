package com.wang.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtils {
    //获得连接Connection--从连接池获取
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //创建LocalThread
    private static  ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 若ThreadLocal中已经有当前线程的conn,则返回该conn,若ThreadLocal中没有当前线程的conn则创建一个线程
     * ，并存入ThreadLocal中
     * @return 当前线程的conn
     * @throws SQLException sql执行异常
     */
    public static Connection getCurrentConnection() throws SQLException {
        //从ThreadLocal寻找当前线程是否有对应的Connection
        Connection connection = threadLocal.get();//默认从当前线程查找是否有conn
        if (connection == null){ //若没有
            //创建新的connection
            connection = getConnection();
            //将资源绑定到ThreadLocal（map）上
            threadLocal.set(connection);
        }
        return connection;
    }

    /**
     * 开启事务
     */
    public static void startAffair() throws SQLException {
       Connection connection =  getCurrentConnection();
       connection.setAutoCommit(false);
    }

    /**
     * 回滚事务
     */
    public static void rollBack() throws SQLException {
        Connection connection =  getCurrentConnection();
        connection.rollback();
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commitAffair() throws SQLException {
        Connection connection =  getCurrentConnection();
        connection.commit();
        //将Connection从ThreadLocal中移除
        threadLocal.remove();
        connection.close();
    }

}
