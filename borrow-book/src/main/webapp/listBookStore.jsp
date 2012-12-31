<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>图书列表</title>
</head>
<body>
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			图书列表
		</font>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
   <tr>
       <th>序号</th>
       <th>书名</th>
       <th>作者</th>
       <th>数量</th>    
       <th>借阅</th>
   </tr>
   <c:forEach items="${books}" var="bookMap">
   		<form name="f1" method="post" action="AddBrwBookServlet">
   		<input type="hidden" name="bookid" value="${bookMap.key }">
   			 <tr>
       			 <td align="center">${bookMap.key }</td>
      			 <td><a href="BookDetailServlet?bookid=${bookMap.key }">${bookMap.value.name }</a></td>
      			 <td>${bookMap.value.author }</td>
      			 <td><input type="text" name="num" value="1" size="4" maxlength="4"/></td>       
      			 <td><input type="submit" value="借阅"/></td>
			</tr>
		</form> 
	</c:forEach>
</table>
<br>
<center>
<input type="button" onclick="document.location='listCart.jsp'" value="确定">
</center>
</body>
</html>

