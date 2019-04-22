package je2_jdbc_mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageLimitDemo {
    private static final  String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final  String URL = "jdbc:mysql://localhost:3306/jdbc_mysql";
    private static final  String USERNAME = "root";
    private static final  String PASSWORD = "123456";
    public static void main(String[] args) {
        try {
            findByPage(3,2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void findByPage(Integer currentPage,Integer pagesize) throws ClassNotFoundException, SQLException {
        Class.forName(DBDRIVER);
        Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        String sql = " SELECT username,password FROM mytab limit ?,? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,(currentPage-1) * pagesize);
        pstmt.setInt(2,pagesize);

        ResultSet rs = pstmt.executeQuery();
        List<String> mylist= new ArrayList<>();
        while (rs.next()){
            mylist.add(rs.getString(1));
            mylist.add(rs.getString(2));
        }

        Iterator iter = mylist.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        rs.close();
        pstmt.close();
        conn.close();
    }
}
