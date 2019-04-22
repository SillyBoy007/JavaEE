package je1_jdbc_oracle;

import java.sql.*;

public class TestDemo {
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
        Statement stmt = conn.createStatement();
        String sql = "SELECT mid,name,age,birthday,note FROM member";  //这里执行普通的sql语句
        ResultSet rs = stmt.executeQuery(sql);
       // int len = stmt.executeUpdate(sql); //执行更新操作
        // System.out.println("已更新:"+len);
        while (rs.next()){  //循环取出返回的每一行数据(取出数据的时候按顺序)
            int mid = rs.getInt("mid");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            Date birthday = rs.getDate("birthday");
            String note = rs.getString("note");
            System.out.println(mid+","+name+","+age+","+birthday+","+note);

        }

        //4.关闭数据库
        rs.close();
        stmt.close();
        conn.close();
    }
}
