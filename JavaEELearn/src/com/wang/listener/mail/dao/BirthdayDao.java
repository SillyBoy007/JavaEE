package com.wang.listener.mail.dao;

import com.wang.listener.mail.vo.User;
import com.wang.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BirthdayDao {
    public List<User> getBirthday(String curentDate) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where birthday like ?";
        List<User> userList = queryRunner.query(sql,new BeanListHandler<User>(User.class),"%"+curentDate);
        return userList;
    }
}
