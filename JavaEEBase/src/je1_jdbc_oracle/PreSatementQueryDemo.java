package je1_jdbc_oracle;

import java.sql.*;
//PreSatement的查询操作
public class PreSatementQueryDemo {
    private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String keyword = "w";
        //1.加载数据库驱动,此时不需要实例化,由容器自己负责管理
        Class.forName(DBDRIVER);
        //2.连接数据库
        Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
        //3.操作数据库
       // String sql = "SELECT mid,name,age,birthday,note FROM member";
        String sql = "SELECT mid,name,age,birthday,note FROM member WHERE name LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+keyword+"%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            int mid = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            Date birthday = rs.getDate(4);
            System.out.println(mid+","+name+","+age+","+birthday);
        }

        //4.关闭数据库
        rs.close();
        pstmt.close();
        conn.close();

    }
}
