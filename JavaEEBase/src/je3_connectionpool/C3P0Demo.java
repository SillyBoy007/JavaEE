package je3_connectionpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Demo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //创建数据源,连接池对象，自动加载文件
        DataSource dataSource = new ComboPooledDataSource(); //加载默认配置
        DataSource dataSource2 = new ComboPooledDataSource("oracle"); //加载oracle配置

      /*  try {
            conn = dataSource.getConnection();
            String sql = "INSERT INTO mytab (username,password) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"C3P0");
            pstmt.setString(2,"ASDAadRE");
            int row = pstmt.executeUpdate();
            System.out.println(row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBConnectionUtil.release(conn,null,pstmt);
        }*/

        try {
            conn = dataSource2.getConnection();
            String sql = "SELECT * FROM myemp";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
