<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>	<!-- 定义struts的标签库 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
  	<s:actionerror cssStyle="color:red"/><!-- 如果存在校验错误，则显示action级别的错误 -->
    <form action="register.action">
    	username:<input type="text" name="username" size="20"><br>
    	password:<input type="password" name="password" size="20"><br>
    	repassword:<input type="password" name="repassword" size="20"><br>
    	age:<input type="text" name="age" size="20"><br>
    	birthday:<input type="text" name="birthday" size="20"><br>
    	graduation:<input type="text" name="graduation" size="20"><br>
    	<input type="submit" value="submit"/>
    </form>
  </body>
</html>
