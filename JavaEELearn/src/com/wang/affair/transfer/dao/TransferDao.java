package com.wang.affair.transfer.dao;

import com.wang.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class TransferDao {
    public int transferIn (String in,double money) throws SQLException {
        Connection connection = DataSourceUtils.getCurrentConnection(); //获得ThreadLocal里的conn
        QueryRunner queryRunner = new QueryRunner();
        String sql = "UPDATE account SET money = money + ? WHERE name = ?";
        int row = queryRunner.update(connection,sql,money,in);
        return row;
    }
    public int transferOut(String out ,double money) throws SQLException {
        Connection connection = DataSourceUtils.getCurrentConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "UPDATE account SET money = money - ? WHERE name = ?";
        int row = queryRunner.update(connection,sql,money,out);
        return row;
    }
}
