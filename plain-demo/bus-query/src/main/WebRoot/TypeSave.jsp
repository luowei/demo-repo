<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TypeSave.jsp' starting page</title>
    
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
  <form id="form1" name="form1" method="post" action="cartype">
  <input type="hidden" name="type" value="save"/>
  <table width="335" height="67" border="1" align="center">
  <tr>
      <td colspan="2">
       <img src="clip_image002.jpg">
      </td>
    </tr>
    <tr>
      <td>线路名称</td>
      <td><input type="text" name="name" /></td>
    </tr>
    <tr>
      <td><input type="submit" name="Submit" value="添加" /></td>
      <td><input type="reset" name="button" value="重置" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
