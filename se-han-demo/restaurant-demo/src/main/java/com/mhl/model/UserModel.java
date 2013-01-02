/*
 * 这是用户表数据模型，用它完成对用户的各种操作,
 * 这里主要编写项目需要的业务操作
 */

package com.mhl.model;
import com.mhl.db.*;
import java.sql.*;

public class UserModel {
	/**
	 * 
	 * @param uid 用编号
	 * @param p 用户密码
	 * @return 该用户的职位，如果该用户不存在则返回空。
	 */
	//验证用户
	public String checkUser(String uid,String p)
	{
		String zhiwei="";
		SqlHelper sqlh=null;
		try {
			//组织sql,和参数列表
			String sql="select clerkInfo.cleZw from login,clerkInfo where login.cleId=clerkInfo.cleId " +
					"and login.cleId=? and login.passwd=?";
			String paras[]={uid,p};
			sqlh=new SqlHelper();
			ResultSet rs=sqlh.query(sql,paras);
			if(rs.next())
			{
				//则取出职位
				zhiwei=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlh.close();//关闭资源
		}
		return zhiwei;
	}
}
