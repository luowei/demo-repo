<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>已选书籍列表</title>
</head>
<body>
<table width="60%" border="0" align="center" bgcolor="FFDC75">
<tr>
	<td align="center">
		<font face="Verdana" size="+1">
			已选书籍列表
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
       <th>操作</th>
   </tr>
   <c:forEach items="${brwCart.brwList}" var="brwBook" >
	 <form name="f1" method="post" action="EditBrwListServlet">
	 <input type="hidden" name="brwBookId" value="${brwBook.book.id }">
   	  <tr>
      	<td align="center">${brwBook.book.id }</td>
       	<td>${brwBook.book.name }</td>
       	<td>${brwBook.book.author }</td>
       	<td><input type="text" name="num" value="${brwBook.num }" size="4" maxlength="4"/></td>      
       	<td align="center">
        	 <input type="submit" value="修改"/>
         	<input type="button" value="清除" onclick="document.location='DelBrwListServlet?bookId=${brwBook.book.id }'"/>
      	</td>
     </tr>
	 </form>
	</c:forEach>
</table>
<br>
<center>
<input type="button" onclick="document.location='ClearCartServlet'" value="清空所选书籍">
&nbsp;<input type="button" onclick="document.location='listBookStore.jsp'" value="继续添加">
&nbsp;<input type="button" onclick="document.location='order.jsp'" value="生成借阅列表">
</center>
</body>
</html>