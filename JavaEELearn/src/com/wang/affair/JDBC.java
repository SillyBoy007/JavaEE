package com.wang.affair;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//通过jdbc去控制事务
public class JDBC {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获得Connection
             conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_mysql?useUnicode=true&characterEncoding=UTF-8","root","123456");

            //手动开启事务
            conn.setAutoCommit(false);

            //3.获得执行平台
            Statement stmt = conn.createStatement();
            //4.操作sql
            int row = stmt.executeUpdate(" INSERT INTO account VALUES (2,'xia',3000) ");

            //若无报错提交事务
            conn.commit();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            try {
                //若出错进行回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
