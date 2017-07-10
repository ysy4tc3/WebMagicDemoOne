package spider;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static BasicDataSource ds;
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

    static{
        try {
            driverName = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/albee";
            username = "root";
            password = "123456";
            int maxactive = 50;
            int maxwait = 50;
            System.out.println("driverName:"+driverName);
            System.out.println("url:"+url);
            System.out.println("username:"+username);
            System.out.println("password:"+password);
            System.out.println("maxactive:"+maxactive);
            System.out.println("maxwait:"+maxwait);
            ds = new BasicDataSource();
            ds.setDriverClassName(driverName);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.setMaxActive(maxactive);
            ds.setMaxWait(maxwait);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
            throws Exception{
        try {

            return ds.getConnection();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void closeConnection(Connection conn)
            throws SQLException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw e;
        }
    }

}