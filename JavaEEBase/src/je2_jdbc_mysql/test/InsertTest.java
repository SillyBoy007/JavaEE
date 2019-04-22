package je2_jdbc_mysql.test;

import je2_jdbc_mysql.util.JDBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
    private static Connection conn;
    private static PreparedStatement pstmt;
    public static void main(String[] args) throws SQLException {
        JDBConnectionUtil dbc = new JDBConnectionUtil();
           conn = JDBConnectionUtil.getConnection();
            String sql = "INSERT INTO mytab (username,password) VALUES (?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"mytest3");
            pstmt.setString(2,"123ewrr");
            System.out.println(pstmt.executeUpdate());
            dbc.release(conn,null,pstmt);
    }
}
