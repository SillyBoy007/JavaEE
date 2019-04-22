package je3_connectionpool;

import je3_connectionpool.util.C3P0Util;
import je3_connectionpool.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * DBUtils的使用，增删改查
 */
public class DBUtilsDemo {
    public static void main(String[] args) {
        testQuery();
    }

    /**
     * 添加用户
     */
    public static void testAddUser(){
        //创建核心类QueryRunner
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        //编写sql
        String sql = "INSERT INTO mytab (username,password) VALUES(?,?)";
        //为占位符设值
       Object [] list = {"dbutils","whuheiq23"};

       //执行操作
        try {
            int rows = qr.update(sql,list);
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新用户
     */
    public static void testUpdate(){
        //创建核心类QueryRunner
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        //编写sql
        String sql = "UPDATE  mytab SET password=? where username=? ";
        //为占位符设值
        Object [] params = {"123456789","dbutils"};

        try {
            int rows = qr.update(sql,params);
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     */
    public static void testDelete(){
        //创建核心类QueryRunner
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        //编写sql
        String sql = "DELETE FROM mytab WHERE username=? ";
        //为占位符设值
        Object [] params = {"dbutils"};

        try {
            int rows = qr.update(sql,params);
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有用户
     */
    public static void  testQuery(){

        try {
            //创建核心类QueryRunner
            QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
            //编写sql
            String sql = "SELECT * FROM mytab ";
            //设置占位符
            Object [] params = {};
            //执行查询
           // BeanListHandler<User> bl = new BeanListHandler<User>(User.class);
           List<Map<String,Object>> list = qr.query(sql,new MapListHandler());
            for (Map<String,Object> map: list){
                System.out.println(map);
            }
        } catch (SQLException e) {
           throw new  RuntimeException(e);
        }
    }

    /**
     * 根据ID查询用户
     */
    public static void testQueryUserById(){
        try {
            //创建核心类QueryRunner
            QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
            //编写sql
            String sql = "SELECT * FROM mytab WHERE username=?";
            //设置占位符
            Object [] params = {"mytest2"};
            //执行查询
            //将结果集中每条记录封装到指定的javabean中,将这些javabean封装到List集合中
            BeanListHandler<User> bl = new BeanListHandler<User>(User.class);
            List<User> users = qr.query(sql,bl,params); //返回结果集合
            for (User user : users){
                System.out.println(user.getUsername()+","+user.getPassword());
            }

           /* BeanHandler<User> bh = new BeanHandler<>(User.class);//返回结果集的第一条记录返回到javabean中
            User user = qr.query(sql,bh,params);
            System.out.println(user.getUsername()+","+user.getPassword());*/

        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
    }
}
