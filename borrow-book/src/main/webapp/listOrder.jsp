<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>已选书目</title>
</head>
<body>
<table width="100%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			已选书目
		</font>
	</td>
</tr>
</table>
<br>
<table align="center" border="1" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
   <tr>
       <th>当前编号</th>
       <th>书的金额</th>
       <th>时间</th>       
       <th>操作</th>
   </tr>
   <c:forEach items="${brwlist}" var="brwBook">
   		<form name="f1" method="post" action="ListBrwListServlet">
   		<input type="hidden" name="brwBookId" value="${brwBook.id }">
   		<input type="hidden" name="cost" value="${brwBook.cost }">
   	 <tr>
      	<td align="center">${brwBook.id }</td>
       	<td>RMB&nbsp;${brwBook.cost }</td>
       	<td>${brwBook.brwBookDate}</td>
		<td align="center"><input type="submit" value="详细信息"/>
							<input type="button" value="删除" 
							onclick="document.location='DelBrwServlet?brwBookId=${brwBook.id }'"/>
		</td>
    </tr>
	</form>
   </c:forEach>
</table>
<br>
<center><input type="button" value="继续添加" onclick="document.location='listBookStore.jsp'"></center>
</body>
</html>