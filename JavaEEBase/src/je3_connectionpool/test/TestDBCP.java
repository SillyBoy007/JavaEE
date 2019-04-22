package je3_connectionpool.test;

import je2_jdbc_mysql.util.JDBConnectionUtil;
import je3_connectionpool.util.DBCPUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDBCP {
    public static void main(String[] args) {
        Connection conn = DBCPUtil.getConnection();
        String sql = "INSERT INTO mytab (username,password) VALUES (?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"dbcptest");
            pstmt.setString(2,"fsfe");
            System.out.println(pstmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBConnectionUtil.release(conn,null,pstmt);
        }
    }
}
