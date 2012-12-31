<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@page import="com.vvvv.entity.BookType;"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>vvvv网上商城</title>
    <link rel="styleSheet" href="<%=request.getContextPath()%>/background/css/body.css" type="text/css" media="all" />
    <style type="text/css">
    	table td {
    		text-align:left;
		}
		
		
    </style>
  </head>
  
  <body>
  <br><br><br>
  <% List<BookType> parent_bookTypes=(List<BookType>)request.getAttribute("parent_bookTypes"); %>
    <form action="<%=request.getContextPath()%>/SaveBookTypeServlet" name="saveBookType" method="post">
    	<table>
    	<caption>新增图书类别信息</caption>
    		<tr>
    			<td>所属类别:</td>
    			<td >
    			<select name=parentID>
   		<%
   				for (BookType parent_bookType : parent_bookTypes)
   				{
   		%>
   					<option><%=parent_bookType.getBookTypeName() %></option>
   		<%		
   				}
   		%>
    			</select>
    			</td>
    			
    		</tr>
    		<tr>
    			<td>类别名称：</td>
    			<td><input name="bookTypeName" size="35"></td>
    		</tr>
    		<tr>
    			<td>是否可用：</td>
    			<td><input type="radio" name="isDelete" value="1" checked>可用 
    				<input type="radio" name="isDelete" value="2">不可用
    			</td>
    		</tr>
    		<tr>
    			<td>描述:</td>
    			<td><textarea rows="4" cols="35" name="context"></textarea></td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="submit"/></td>
    			
    			<td><input type="reset" value="reset"/></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
