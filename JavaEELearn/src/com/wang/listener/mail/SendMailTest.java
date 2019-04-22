package com.wang.listener.mail;

import javax.mail.MessagingException;

/**
 * 发送邮件测试
 */
public class SendMailTest {
    public static void main(String[] args) {
        try {
            MailUtils.sendMail("lucy@wangxiayun.com","程序发送邮件测试","Hello,mail!!!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
