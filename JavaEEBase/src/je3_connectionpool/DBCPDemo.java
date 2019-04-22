package je3_connectionpool;

import je3_connectionpool.util.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBCPDemo {
    public static void main(String[] args) {
        DataSource dataSource = DBCPUtil.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        //编写sql
        String sql = "SELECT * FROM mytab ";
        //设置占位符
        Object [] params = {};
        //执行查询
        // BeanListHandler<User> bl = new BeanListHandler<User>(User.class);
        List<Map<String,Object>> list = null;
        try {
            list = queryRunner.query(sql,new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Map<String,Object> map: list){
            System.out.println(map);
        }
    }
}
