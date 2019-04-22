package com.wang.affair;

import com.wang.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBUtils使用事务
 */
public class DBUtils {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //这里使用无参构造
            QueryRunner queryRunner = new QueryRunner();
            conn = C3P0Util.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //使用无参C3P0Util.getConnection()需要传入conntion
            queryRunner.update(conn,"UPDATE account SET money=10000 WHERE name='xia'");
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
