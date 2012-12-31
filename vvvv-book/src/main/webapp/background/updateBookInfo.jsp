<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@page import="com.vvvv.entity.BookInfo"%>
<%@page import="com.vvvv.entity.BookType"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改图书信息-vvvv网上商城</title>
    <link rel="styleSheet" href="<%=request.getContextPath()%>/background/css/body.css" type="text/css" media="all" />
    <style type="text/css">
    	table td {
    		text-align:left;
		}
		
    </style>
  </head>
  
  <body onload="init()">&nbsp; 
  <br><br><br><br><br>
  	<%
  		BookInfo book = (BookInfo)request.getAttribute("book");
  	%>
  	
    <form action="<%=request.getContextPath()%>/UpdateBookInfoServlet" name="updateBookType" method="post">
    	<table>
    	<caption>修改图书信息</caption>
    		<tr>
    			<td>图书编号:</td>
    			<td ><input name="bookId" size="35" value="<%=book.getBookId()%>" readonly></td>
    		</tr>
    		<tr>
    			<td>图书名称:</td>
    			<td ><input name="bookName" size="35" value="<%=book.getBookName()%>"></td>
    		</tr>
    		<tr>
    			<td>图书类别编号：</td>
    			<td>
    			<select name="bookTypeId" id="bookTypeSelect" onclick="option_newBook()">
    				<%
    				List<BookType> bookTypes=(List<BookType>)request.getAttribute("bookTypes");
    				for (BookType bookType : bookTypes)
    				{
    				%>
    					<option value="<%=bookType.getBookTypeId() %>"
    				<%
    				//		if(bookType.getBookTypeId()==book.getBooktypeId()){
   					%>
   					<%// 			selected="selected"   %>
   					<%
    				//	} 
    				%>
    					><%=bookType.getBookTypeName() %></option>
    				<%
    				}
    				%>
    				<option>--新增图书类别--</option>
    			</select>
				</td>
				<script type="text/javascript">
    			function init(){
    				document.updateBookType.bookTypeId.value='<%=book.getBooktypeId() %>';
    			}
  				</script>
    		</tr>
    		<tr>
    			<td>出版社名称：</td>
    			<td><input name="pbName" size="35" value="<%=book.getPbName()%>">*</td>
    		</tr>
    		<tr>
    			<td>作者：</td>
    			<td><input name="author" size="35" value="<%=book.getAuthor()%>">*</td>
    			
    		</tr>
    		<tr>
    			<td>大图片：</td>
    			<td><input type="file" name="bigImg" size="35" value="<%=book.getBigImg() %>">&nbsp;&nbsp;文件上传</td>
    		</tr>
    		<tr>
    			<td>小图片：</td>
    			<td><input type="file" name="smallImg" size="35" value="<%=book.getSmallImg() %>">&nbsp;&nbsp;文件上传</td>
    		</tr>
    		<tr>
    			<td>价钱：</td>
    			<td><input name="price" size="35" value="<%=book.getPrice() %>"></td>
    		</tr>
    		<tr>
    			<td>vvvv特价：</td>
    			<td><input name="vvvvPrice" size="35" value="<%=book.getVvvvprice() %>"></td>
    		</tr>
    		<tr>
    			<td>出版日期：</td>
    			<td><input name="pbDate" size="35" value="<%=book.getPbdate() %>"></td>
    		</tr>
    		<tr>
    			<td>图书状态：</td>
    			<td><input type="radio" name="bookStates" value="1"
	    				<%if(book.getBookStatus() == 1) {%>
	    					checked		
	    				<%} %> 
    				>可用 
    				<input type="radio" name="bookStates" value="2"
	    				<%if(book.getBookStatus() == 2) {%>
		    					checked		
		    			<%} %> 
    				>上架
    				<input type="radio" name="bookStates" value="3"
	    				<%if(book.getBookStatus() == 3) {%>
		    					checked		
		    			<%} %>
    				>不可用
    			</td>
    		</tr>
    		<tr>
    			<td>描述:</td>
    			<td><textarea rows="4" cols="35" name="context"><%=book.getContext() %>
    			</textarea></td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="submit"/></td>
    			
    			<td><input type="reset" value="reset"/></td>
    		</tr>
    	</table>
    </form>
  </body>
  
</html>
