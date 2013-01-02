<!--主界面 -->
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Main.jsp' starting page</title>
    
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
	<%
		String u=request.getParameter("username");
		//System.out.println("Main.jsp得到的username="+u);
	 %>
  <center>
	<!-- 引入一张图片 -->
    <img src="imgs/1.gif"/>
    <hr>
    <h1>请选择操作</h1>
     <a href="UserClServlet?pageNow=1&flag=fenye&username=<%=u %>" >管理用户</a><br/>
     <!-- flag标志告诉控制器，进行分页 -->
     <!-- 注意这里变量<%=u %>要放在双引号里边，直接赋给username -->
     <a href="addUser.jsp?username=<%=u %>">添加用户</a><br/>
     <a href="#">查找用户</a><br/>
     <a href="#">注销用户</a><br/>

    <hr>
    <!-- 引入一张图片 -->
    <img src="imgs/logo.gif"/>
    </center>
  </body>
</html>
