<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>用户登陆</title>
</head>
<body><center>
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			用户登陆
		</font>
	</td>
</tr>
</table>
<table align="center">
	<tr><td align="center">
	<font color="red">
		${message }
	</font>
	</td></tr>
</table>
<br>

<form name="f1" method="post" action="LoginServlet">

	<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
    <tr>
       <td align="center">用户名</td>
       <td><input type="text" name="name" style="width:150px" maxlength="20">
	   </td>       
	</tr>
	<tr>
	   <td align="center">密码</td>
       <td><input type="password" name="password" style="width:150px" maxlength="20">
	</td>       
	</tr>
</table>
	<br>
	<input type="submit" value="提交">&nbsp;
	<input type="reset" value="重置">&nbsp;
	<a href="register.jsp">注册新用户</a>
</form>
</center></body>
</html>
