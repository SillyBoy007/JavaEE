package com.wang.listener.birthday;

import com.wang.listener.mail.MailUtils;
import com.wang.listener.mail.service.BirthdayService;
import com.wang.listener.mail.vo.User;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 生日监听器
 */
public class BirthdayListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //当web应用创建开启任务调度---在用户的生日当天发送邮件
        //开启定时器
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //为当天生日的用户发邮件
                //获得当前生日的人
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                String currentDate = format.format(new Date());
                //提取当前时间从数据库查询今天生日的人
                BirthdayService birthdayService = new BirthdayService();
                List<User> userList = null;
                try {
                    userList = birthdayService.getBirthday(currentDate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //发邮件
                if (userList != null && userList.size()>0){
                    for (User u : userList){
                        try {
                            MailUtils.sendMail(u.getEmail(),"生日祝福","祝你生日快乐");
                            System.out.println("邮件发送完毕");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        },new Date(),1000*30); //实际开发中，起始时间是一个固定点,间隔时间是1天
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
