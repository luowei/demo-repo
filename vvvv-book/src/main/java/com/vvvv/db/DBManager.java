package com.vvvv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * B/S应用程序连接肯定不是单态的，这只是演示，后面一定要使用数据库连接池
 *
 * @author luowei
 * 懒汉单态模式
 */
public class DBManager
{
    private static Connection con=null;

    /**
     * 构造方法私有，外部不能创建对象
     */
    private DBManager()
    {

    }
    public synchronized static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(con==null)
        {
            Class.forName(Config.DRIVER);
            con=DriverManager.getConnection(Config.URL+Config.DBNAME,Config.UNAME,Config.PWD);
        }
        return con;
    }
}
