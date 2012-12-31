<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>确认借阅</title>
</head>
<body>

<h3 align="center">确认借阅</h3>
<form name="f1" method="post" action="ConfirmBrwServlet"> 
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			用户信息
		</font>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
  <tr>
       <td>姓名</td>
       <td><input type="text" size="20" maxlength="20" name="name" value="${sessionScope.customer.name }" disabled/></td>
  </tr> 
  <tr>
       <td>邮编</td>
       <td><input type="text" size="20" maxlength="20" name="zip" value="${sessionScope.customer.zip }"/></td>
  </tr> 
  <tr>
       <td>地址</td>
       <td><input type="text" size="20" maxlength="20" name="address" value="${sessionScope.customer.address }"/></td>
  </tr> 
  <tr>
       <td>电话</td>
       <td><input type="text" size="20" maxlength="20" name="telephone" value="${sessionScope.customer.telephone }"/></td>
  </tr> 
  <tr>
       <td>电子信箱</td>
       <td><input type="text" size="20" maxlength="20" name="email" value="${sessionScope.customer.email }"/></td>
  </tr>
</table>
<br>            
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			借阅清单(<a href="listCart.jsp">修改</a>)
		</font>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
   <tr>
       <th>编号</th>
       <th>书名</th>
       <th>作者</th>
       <th>数量</th>       
  </tr>
  <c:forEach items="${brwCart.brwList}" var="brwList">
		<tr>
       		<td align="center">${brwList.book.id }</td>
      		<td>${brwList.book.name}</td>
       		<td>${brwList.book.author}</td>
      	 	<td align="center">${brwList.num}</td>       
       </tr>
  </c:forEach>
</table>
<br>
<center>
	<input type="submit" value="确认">&nbsp;
	<input type="button" onclick="document.location='listBookStore.jsp'" value="继续借阅">
</center>
</form> 
</body>
</html>