package je2_jdbc_mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 根据Properties对象和输入流获取properties文件值
 */
public class PropertiesObj {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    public static void main(String[] args) throws IOException {
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


        try {
            doRegister("properties","23ewqejqj");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doRegister(String myusername,String mypwd) throws ClassNotFoundException, SQLException {

        //注册驱动
        Class.forName(driver);
        //获取连接
        Connection conn = DriverManager.getConnection(url,username,password);
        //操作数据库
        String sql = "INSERT INTO mytab (username,password) VALUES (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,myusername);
        pstmt.setString(2,mypwd);
        System.out.println(pstmt.executeUpdate());
        release(null,pstmt,conn);
    }
    public static void release(ResultSet rs, PreparedStatement psm,Connection conn ){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psm!=null){
            try {
                psm.close();
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
