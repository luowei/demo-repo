<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>借阅单明细</title>
</head>
<body>
<h3 align="center">借阅单明细</h3>
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
       <td>${customer.name }</td>
  </tr> 
  <tr>
       <td>邮编</td>
       <td>${customer.zip }</td>
  </tr> 
  <tr>
       <td>地址</td>
       <td>${customer.address }</td>
  </tr> 
  <tr>
       <td>电话</td>
       <td>${customer.telephone }</td>
  </tr> 
  <tr>
       <td>电子信箱</td>
       <td>${customer.email }</td>
  </tr>
</table>
<br>            
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			借阅清单
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
       <th>日期</th>
       <th>数量</th>     
  </tr>
  <c:forEach items="${brwCart.brwList}" var="brwlist">
  	 <tr>
       		<td align="center">${brwlist.book.id }</td>
      	 	<td>${brwlist.book.name }</td>
       		<td>${brwlist.book.author }</td>
       		<td>${brwlist.brwBook.brwBookDate}</td>
       		<td align="center">${brwlist.num }</td>       
      </tr>
   </c:forEach>
</table>
<br>
<center>
<input type="button" onclick="document.location='listBookStore.jsp'" value="再次借阅">
</center>
</body>
</html>
