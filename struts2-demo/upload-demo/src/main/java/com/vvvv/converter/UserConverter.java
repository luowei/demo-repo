package com.vvvv.converter;

import com.vvvv.bean.User;
import ognl.DefaultTypeConverter;

import java.util.Map;
import java.util.StringTokenizer;

public class UserConverter extends DefaultTypeConverter
{
	@Override
	public Object convertValue(Map context, Object value, Class toType)
	{
		if(User.class == toType)
		{
			String[] str = (String[])value;
			
			String firstValue = str[0];
			
			StringTokenizer st = new StringTokenizer(firstValue, ";");
			
			String username = st.nextToken();
			String password = st.nextToken();
			
			User user = new User();
			
			user.setUsername(username);
			user.setPassword(password);
			
			return user;		
		}
		else if(String.class == toType)
		{
			User user = (User)value;
			
			String username = user.getUsername();
			String password = user.getPassword();
			
			String userInfo = "username: " + username + ", password: " + password;
			
			return userInfo;
		}
		
		return null;
	}
}
