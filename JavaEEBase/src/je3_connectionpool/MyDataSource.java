package je3_connectionpool;

import je2_jdbc_mysql.util.JDBConnectionUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 *自定义连接池,工具类
 */
public class MyDataSource implements DataSource{
    //1.创建容器,用于存储Connection对象,由于要频繁进行更新操作,采用LinkedList集合
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //2.创建5个连接,翻入容器
    static {  //静态代码块是自动执行的
        for(int i = 0;i<5;i++) {
            Connection conn = JDBConnectionUtil.getConnection();
            pool.add(conn);
        }
    }

    /**
     * 重写获取连接的方法
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {

        //3.使用前先判断
        if(pool.size() == 0){
            //若没有先创建
            for(int i = 0;i<5;i++){
            Connection conn =  JDBConnectionUtil.getConnection();
                //将改造过的放入池子
                pool.add(conn);
            }
        }
       Connection conn = pool.remove(0);//从池子里获取1个连接对象
        return conn;
    }

    /**
     * 将连接对象归还给连接池
     * @param conn 连接对象
     */
    public void backConnect(Connection conn){
        pool.add(conn);
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
