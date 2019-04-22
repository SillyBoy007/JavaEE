package com.wang.affair.transfer.service;

import com.wang.affair.transfer.dao.TransferDao;
import com.wang.utils.DataSourceUtils;

import java.sql.SQLException;

/**
 * 在业务层进行事务控制
 */
public class TransferService {
    public boolean isTransfer(String out,String in, double money){
        TransferDao transferDao = new TransferDao();
        boolean isTransfer = true;
        try {

             DataSourceUtils.startAffair();//开启事务
             int rowOut = transferDao.transferOut(out,money);
             int rowIn = transferDao.transferIn(in,money);
             if (!(rowIn >0 && rowOut>0)){ //若有一个执行错误
                  isTransfer = false;
                  DataSourceUtils.rollBack();  //回滚事务
             }
        } catch (Exception e) {
            isTransfer = false;
            try {
                DataSourceUtils.rollBack(); //回滚事务
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                DataSourceUtils.commitAffair(); //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         return isTransfer;
    }
}
