<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <h2><font color="blue">用户注册</font></h2>
    
    <s:actionerror cssStyle="color:red"/>
    
    ----------------------------------------
    
    <s:fielderror cssStyle="color:blue"></s:fielderror>
    
    <!-- 未使用struts标签库 --><!-- 
    <form action="register.action">
    
    username: <input type="text" name="username" size="20"><br>
    password: <input type="password" name="password" size="20"><br>
    repassword: <input type="password" name="repassword" size="20"><br>
    age: <input type="text" name="age" size="20"><br>
    birthday: <input type="text" name="birthday" size="20"><br>
    graduation: <input type="text" name="graduation" size="20"><br>
    <input type="submit" value="submit"/>
    </form>
      -->
     
    <!-- 使得Struts标签库 --><!-- 
    
     -->
    <s:form action="register.action" theme="simple">
    username: <s:textfield name="username" label="username"></s:textfield><br>
    password: <s:password name="password" label="password"></s:password><br>
    repassword: <s:password name="repassword" label="repassword"></s:password><br>
    age: <s:textfield name="age" label="age"></s:textfield><br>
    birthday: <s:textfield name="birthday" label="birthday"></s:textfield><br>
    graduation: <s:textfield name="graduation" label="graduation"></s:textfield><br>
    <s:submit value="submit"></s:submit>
    </s:form>
     
  </body>
</html>
