package je1_jdbc_oracle;

import java.sql.*;

//分页
public class PageQueryDemo {
    private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int linesize = 5;
        int currentpage = 2;
        //1.加载数据库驱动,此时不需要实例化,由容器自己负责管理
        Class.forName(DBDRIVER);
        //2.连接数据库
        Connection conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
        //3.操作数据库
        String sql = " SELECT * FROM ( "
                + " SELECT mid,name,birthday,age,note,ROWNUM rn "
                + " FROM member "
                +" WHERE ROWNUM<=?)temp "
                +" WHERE temp.rn>? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,linesize * currentpage);
        pstmt.setInt(2,(currentpage - 1) * linesize);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            int mid = rs.getInt(1);
            String name = rs.getString(2);
            Date birthday = rs.getDate(3);
            int age = rs.getInt(4);
            String note = rs.getString(5);
            System.out.println(mid+","+name+","+age+","+birthday+","+note);
        }
        //4.关闭数据库
        rs.close();
        pstmt.close();
        conn.close();
    }
}
