<!-- 登录界面 -->
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="ff9900">
  <center>
  <% 
  	String errNo=request.getParameter("errNo");
  	if(errNo!=null)
  	{
  		if(errNo.equals("1"))
  		{
  			out.println("<font color=red size=7>登录错误，请输入正确的用户名和密码！</font><br>");
  		}
  	}
   %>
	<!-- 引入一张图片 -->
    <img src="imgs/1.gif"/>
    <hr>
     用户登录 <br>
    <form action="LoginClServlet" method="post">
   		 用户名：<input type="text" name="username"/><br>
   		 密&nbsp;&nbsp;码：<input type="password" name="passwd"/><br>
   		 <input type="submit" value="登录"/>
   		 <input type="reset" value="重置"/>
    </form>
    <hr>
    <!-- 引入一张图片 -->
    <img src="imgs/logo.gif"/>
    </center>
  </body>
</html>
