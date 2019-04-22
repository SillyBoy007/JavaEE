package je2_jdbc_mysql.util;

import je2_jdbc_mysql.PropertiesObj;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接工具类,调用了本类的getDBInfo方法,输入流读取db.properties的值
 */
public class JDBConnectionUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    public static Connection getConnection() {
        Connection  conn = null;
        try {
            getDBInfo();
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
     }
    public static void getDBInfo() throws IOException {
        //通过当前类取得加载器
        ClassLoader classLoader = PropertiesObj.class.getClassLoader();
        //通过类加载器获得一个输入流
        InputStream input = classLoader.getResourceAsStream("db.properties");
        //创建一个Properties对象
        Properties props = new Properties();
        //加载输入流
        props.load(input);
        //通过Properties实例化对象获取相关值
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");

    }
    public static void release(Connection conn, ResultSet rs, PreparedStatement pstmt){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
