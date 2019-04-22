package com.wang.listener.mail.service;

import com.wang.listener.mail.dao.BirthdayDao;
import com.wang.listener.mail.vo.User;

import java.sql.SQLException;
import java.util.List;

public class BirthdayService {
    public List<User> getBirthday(String curentDate) throws SQLException {
        BirthdayDao birthdayDao = new BirthdayDao();
        List<User> userList = birthdayDao.getBirthday(curentDate);
        return userList;
    }
}
