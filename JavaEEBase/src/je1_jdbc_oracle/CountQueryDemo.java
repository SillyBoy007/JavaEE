package je1_jdbc_oracle;

import java.sql.*;

public class CountQueryDemo {
    private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载数据库驱动,此时不需要实例化,由容器自己负责管理
        Class.forName(DBDRIVER);
        //2.连接数据库
        Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
        //3.操作数据库
        String sql = "SELECT COUNT(mid) FROM member WHERE NAME LIKE ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+""+"%");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            System.out.println( rs.getInt(1));
        }
        //4.关闭数据库
        rs.close();
        pstmt.close();
        conn.close();
    }
}
