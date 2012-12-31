package com.vvvv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.vvvv.entity.EntityMapping;

//代替原来的DBManager
public class JdbcTemplate
{
    /**
     * 用来执行insert update delete 语句
     * @param sql 是带占位符的sql语句
     * @param values	sql占个占位符具体的值
     * @return
     * @throws SQLException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    //public int update(String sql,Object[] values) throws SQLException
    public int update(String sql,Object... values) throws SQLException
    //Object... values表示可变参数
    {
        PreparedStatement psta=null;
        Connection con=null;
        int row=0;
        try
        {
            con=DBManager.getConnection();
            psta = con.prepareStatement(sql);
            //给占位符赋值
            //if(values!=null && values.length!=0)	//因为传过来的values会被自动转化成数组类型，所以对values数组可以不作判断
            //{
            for (int i = 0; i < values.length; i++)
            {
                psta.setObject(i+1,values[i]);
            }
            //}
            row=psta.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(psta!=null)
            {
                psta.close();
                psta=null;
            }
        }
        return row;
    }

    /**
     * 得到某个表符合条件的数据的总行数
     * @param sql
     * @return
     * @throws SQLException
     */
    public int query(String sql) throws SQLException
    {
        int result=0;
        Connection con=null;
        PreparedStatement psta = null;
        ResultSet rs=null;
        try
        {
            con=DBManager.getConnection();
            psta=con.prepareStatement(sql);
            rs=psta.executeQuery();
            if(rs.next())
            {
                result=rs.getInt(1);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(rs!=null)
            {
                rs.close();
                rs=null;
            }
            if(psta!=null)
            {
                psta.close();
                psta=null;
            }
        }
        return result;
    }


    /**
     * 查询
     * @throws SQLException
     */
    public List<? extends Object> query(String sql,EntityMapping mapping,Object... values) throws SQLException
    //public List<Object> query(String sql,EntityMapping mapping,Object... values) throws SQLException
    {
        PreparedStatement psta=null;
        Connection con=null;
        ResultSet rs=null;
        List<Object> list=new Vector<Object>();
        try
        {
            con=DBManager.getConnection();
            psta = con.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) //给sql语句中的?赋值
            {
                psta.setObject(i+1,values[i]);
            }
            rs=psta.executeQuery();
            while(rs.next())
            {
                list.add(mapping.mapping(rs));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally
        {
            if(rs!=null)
            {
                rs.close();
                rs=null;
            }
            if(psta!=null)
            {
                psta.close();
                psta=null;
            }
        }
        return list;
    }
}
