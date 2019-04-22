package com.wang.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBCP连接池工具类
 */
public class DBCPUtil {
    private static DataSource dataSource;
    static {
        try {
            //加载properties文件输入流
            InputStream is = DBCPUtil.class.getClassLoader().getResourceAsStream("db.properties");
            //2.加载输入流
            Properties props = new Properties();
            props.load(is);
            System.out.println(props.toString());
            //3.创建数据源
            dataSource = BasicDataSourceFactory.createDataSource(props);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
