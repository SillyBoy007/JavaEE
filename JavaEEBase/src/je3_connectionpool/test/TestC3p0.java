package je3_connectionpool.test;

import je2_jdbc_mysql.util.JDBConnectionUtil;
import je3_connectionpool.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestC3p0 {
    public static void main(String[] args) {
        Connection conn = C3P0Util.getConnection();
        String sql = "INSERT INTO mytab (username,password) VALUES (?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"c3p0util");
            pstmt.setString(2,"asd");
            System.out.println(pstmt.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBConnectionUtil.release(conn,null,pstmt);
        }

    }
}
