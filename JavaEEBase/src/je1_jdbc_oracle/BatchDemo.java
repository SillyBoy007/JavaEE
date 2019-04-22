package je1_jdbc_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

//执行批处理,事务提交

public class BatchDemo {
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
        conn.setAutoCommit(false); //取消事务自动提交
        try {
            stmt.addBatch("INSERT INTO member(mid,name) VALUES(myseq.nextval,'测试A')");
            stmt.addBatch("INSERT INTO member(mid,name) VALUES(myseq.nextval,'测试B')");
            stmt.addBatch("INSERT INTO member(mid,name) VALUES(myseq.nextval,'测试C')");
            stmt.addBatch("INSERT INTO member(mid,name) VALUES(myseq.nextval,'测试D')");
            int result[] = stmt.executeBatch(); //执行批处理
            System.out.println(Arrays.toString(result));
            conn.commit();//如果没有错误进行提交
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback(); //若出现异常进行回滚
        }

        stmt.close();
        conn.close();

    }
}
