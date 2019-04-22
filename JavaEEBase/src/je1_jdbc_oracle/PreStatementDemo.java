package je1_jdbc_oracle;

import java.sql.*;
import java.util.Date;


public class PreStatementDemo {
    private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String name = "eqw";
        Date birthday = new Date();
        int age = 13;
        String note = "gdsg";

        //1.加载数据库驱动,此时不需要实例化,由容器自己负责管理
        Class.forName(DBDRIVER);
        //2.连接数据库
        Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
        //3.操作数据库

        String sql = "INSERT INTO member (mid,name,birthday,age,note) VALUES (myseq.nextval,?,?,?,?)";
        PreparedStatement prestmt = conn.prepareStatement(sql);//
        prestmt.setString(1,name);
        prestmt.setDate(2, new java.sql.Date(birthday.getTime())); //先变为long再变为sql.date
        prestmt.setInt(3,age);
        prestmt.setString(4,note);
        int len = prestmt.executeUpdate();//在这里执行
        System.out.println(len);

        //4.关闭数据库
        prestmt.close();
        conn.close();
    }
}
