package je2_jdbc_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 配置资源文件,通过访问资源文件连接数据库
 */
public class ResorceBundleDemo {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("db"); //获取资源文件
        driver = rb.getString("driver");
        url = rb.getString("url");
        username = rb.getString("username");
        password = rb.getString("password");


        try {
            doRegister("resource","7834wew");
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

        pstmt.close();
        conn.close();
    }

}
