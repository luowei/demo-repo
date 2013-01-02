<%@ page language="java" import="java.util.*,java.sql.*,user.model.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginCl.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	//接收用户名和密码，完成对用户的验证
    String u=request.getParameter("username"); 
    String p=request.getParameter("passwd");
    
    //到数据库中去验证用户
  	//调用UserBeanCl的方法，完成对用户的验证
  	UserBeanCl ubc=new UserBeanCl();
  	
  	if(ubc.checkUser(u,p))
  	{
  		response.sendRedirect("wel.jsp?username="+u);
  	}else
  	{
  		response.sendRedirect("login.jsp?errNo=1");
  	}
    
	%>
    
    
  </body>
</html>
