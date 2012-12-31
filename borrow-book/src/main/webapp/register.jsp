<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户注册</title>
</head>
<body>
<form name="f1" method="post" action="RegisterServlet"> 
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font color="red" face="Verdana" size="+1">
			用户注册
		</font>
	</td>
</tr>
</table>
<table align="center">
	<tr><td align="center">
	<font color="red">
		${message}
	</font>
	</td></tr>
</table>
<br>
<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
  <tr>
       <td>姓名</td>
       <td><input type="text" size="20" style="width:150px" maxlength="20" name="name"/></td>
  </tr> 
  <tr>
       <td>密码</td>
       <td><input type="password" size="20" style="width:150px" maxlength="20" name="password"/></td>
  </tr> 
  <tr>
       <td>邮编</td>
       <td><input type="text" size="20" maxlength="20" name="zip"/></td>
  </tr> 
  <tr>
       <td>地址</td>
       <td><input type="text" size="20" maxlength="20" name="address"/></td>
  </tr> 
  <tr>
       <td>电话</td>
       <td><input type="text" size="20" maxlength="20" name="telephone"/></td>
  </tr> 
  <tr>
       <td>电子信箱</td>
       <td><input type="text" size="20" maxlength="20" name="email"/></td>
  </tr>
</table>
<br>            
<center>
	<input type="submit" value="注册">&nbsp;
	<input type="reset" value="重置">
</center>
</form> 
</body>
</html>