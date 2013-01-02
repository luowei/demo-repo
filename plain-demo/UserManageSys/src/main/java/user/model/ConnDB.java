/**
 * 得到数据库的连接
 */

package user.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnDB {
    private Connection ct=null;
    public Connection getConn()
    {
        try {
            //1.加载驱动
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
            //2.得到连接
            ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/test_user","root","root");
            //ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databaseName=testusr","sa","luowei");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ct;
    }
}
