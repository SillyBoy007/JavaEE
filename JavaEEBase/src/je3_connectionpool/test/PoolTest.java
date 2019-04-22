package je3_connectionpool.test;

import je3_connectionpool.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PoolTest {
    public static void main(String[] args) {
        MyDataSource das = new MyDataSource();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn =  das.getConnection();
            String sql = "INSERT INTO mytab (username,password) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"pooltest2");
            pstmt.setString(2,"213isawe");
            int rows = pstmt.executeUpdate();
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            das.backConnect(conn);
        }
    }
}
