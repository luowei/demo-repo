<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateUser.jsp' starting page</title>
    
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
		String pageNow=request.getParameter("pageNow");
		//String flag=request.getParameter("flag");
		//System.out.println("Main.jsp得到的username="+u);
	 %>
	 欢迎您：<%=u %><br>
	 <a href="Main.jsp?username=<%=u %>">返回主界面</a>
	 <a href="UserClServlet?username=<%=u %>&UserClServlet=<%=u %>&pageNow=<%=pageNow %>&flag=fenye">返回上一页</a>
  	<center>
  		
		<!-- 引入一张图片 -->
	    <img src="imgs/1.gif"/>
	    <hr>
	    <h1>修改用户信息</h1>
	    <form action="UserClServlet?pageNow=1&flag=updateUser&username=<%=u %>" method="post">
	    	<table border="1">
	    		<tr bgcolor="pink"><td>用户Id</td><td><input readonly type="text" name="userId" value="<%=request.getParameter("userId") %>"/></td></tr>
	    		<tr bgcolor="pink"><td>用户名</td><td><input type="text" name="userName" value="<%=request.getParameter("userName") %>"/></td></tr>
	    		<tr bgcolor="silver"><td>密码</td><td><input type="text" name="passWd" value="<%=request.getParameter("passWd") %>"/></td></tr>
	    		<tr bgcolor="pink"><td>电子邮件</td><td><input type="text" name="email" value="<%=request.getParameter("email") %>"/></td></tr>
	    		<tr bgcolor="silver"><td>级别</td><td><input type="text" name="grade" value="<%=request.getParameter("grade") %>"/></td></tr>
	    	</table>
	    	<input type="submit" value="修改信息"/>&nbsp;&nbsp;
	    </form>
	
	    <hr>
	    <!-- 引入一张图片 -->
	    <img src="imgs/logo.gif"/>
    </center>
  </body>
</html>
