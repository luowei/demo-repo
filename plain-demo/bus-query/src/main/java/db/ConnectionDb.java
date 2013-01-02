package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 用于连接数据库
 *
 * @author Administrator
 */
public class ConnectionDb {
    //mysql 帐号默认是root
    private String username;//帐号
    private String pass;//密码

    public ConnectionDb(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public Connection getConnection() {
        //try捕获异常\错误
        try {
            //连接数据库语句
            //连接数据库必须将此数据的连接驱动加载到内存中
            new com.mysql.jdbc.Driver();
            //参数1 连接语句 参数2用户登陆帐号 参数3密码

            String jdbc = "jdbc:mysql://localhost:3306/mytest";
            return DriverManager.getConnection(jdbc, username, pass);
        } catch (Exception ex) {
            //打印错误信息
            ex.printStackTrace();
        }
        //返回null表示连接失败
        return null;
    }

    public static void main(String arg[]) {
        ConnectionDb db = new ConnectionDb("root", "root");
        Connection con = db.getConnection();
        System.out.println(con);
    }
}