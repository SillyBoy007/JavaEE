package com.wang.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0工具类
 */
public class C3P0Util {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    public static DataSource getDataSource(){
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); //正常就返回结果,不正常用异常处理
        }
    }
}
