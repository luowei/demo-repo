<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>图书详细信息</title>
  </head>
  
  <body>
   <table width="60%" border="0" align="center" bgcolor="FFDC75">
	<tr>
		<td align="center">
			<font face="Verdana" size="+1">
				图书明细(书号${book.id })
			</font>
		</td>
	</tr>
	</table>
<br>
<table align="center" border="0" bordercolor="FFDC75" cellpadding="3" cellspacing="3">
  <tr>
  <td colspan="2">
  <img src="images/${book.img }" width="228" height="300" border="1" align="center" vspace="5" hspace="5">
  </td>
  </tr>
  <tr>
       <td><b>编号:</b></td><td>${book.id }</td>     
  </tr>  
  <tr>
       <td><b>书名:</b></td><td>${book.name }</td>
  </tr>
  <tr>
       <td><b>作者:</b></td><td>${book.author }</td>
  </tr>
  <tr>
       <td colspan="2"><b>简介:</b></td>
  </tr>
  <tr>
  		<td colspan="2">${book.detail }</td>
  </tr>
</table>
<br>
<center><a href="listBookStore.jsp">返回</a></center>
  </body>
</html>
