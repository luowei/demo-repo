<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@page import="com.vvvv.entity.BookType"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>����ͼ����Ϣ-vvvv�����̳�</title>
    <link rel="styleSheet" href="<%=request.getContextPath()%>/background/css/body.css" type="text/css" media="all" />
    <style type="text/css">
    	table td {
    		text-align:left;
		}
    </style>
  </head>
  
  <body>
  <br><br><br>
  
    <form action="<%=request.getContextPath()%>/SaveBookInfoServlet" name="saveBookType" method="post">
    	<table>
    	<caption>����ͼ����Ϣ</caption>
    		<tr>
    			<td>ͼ������:</td>
    			<td ><input name="bookName" size="35" ></td>
    		</tr>
    		<tr>
    			<td>ͼ������ţ�</td>
    			<td>
    			<select name="bookTypeId" id="bookTypeSelect" onclick="option_newBook()">
    				<%
    				List<BookType> bookTypes=(List<BookType>)request.getAttribute("bookTypes");
    				for (BookType bookType : bookTypes)
    				{
    				%>
    					<option value=<%=bookType.getBookTypeId() %>><%=bookType.getBookTypeName() %></option>
    				<%}
    				%>
    				<option index="1">--����ͼ�����--</option>
    			</select>
    			</td>
    		</tr>
    		<script type="text/javascript">
    					function option_newBook(){
        					var i=Math.floor(event.offsetY/30);
        					with(bookTypeSelect)if(i.index==1){
    							self.location='saveBookType.jsp';
        					}
        				}
    				</script>
    		<tr>
    			<td>���������ƣ�</td>
    			<td><input name="pbName" size="35">*</td>
    		</tr>
    		<tr>
    			<td>���ߣ�</td>
    			<td><input name="author" size="35">*</td>
    		</tr>
    		<tr>
    			<td>��ͼƬ��</td>
    			<td><input type="file" ame="bigImg" size="35">&nbsp;&nbsp;�ļ��ϴ�</td>
    		</tr>
    		<tr>
    			<td>СͼƬ��</td>
    			<td><input type="file" name="smallImg" size="35">&nbsp;&nbsp;�ļ��ϴ�</td>
    		</tr>
    		<tr>
    			<td>��Ǯ��</td>
    			<td><input name="price" size="35"></td>
    		</tr>
    		<tr>
    			<td>vvvv�ؼۣ�</td>
    			<td><input name="vvvvPrice" size="35"></td>
    		</tr>
    		<tr>
    			<td>�������ڣ�</td>
    			<td><input name="pbDate" size="35"></td>
    		</tr>
    		<tr>
    			<td>ͼ��״̬��</td>
    			<td><input type="radio" name="bookStates" value="1" checked>���� 
    				<input type="radio" name="bookStates" value="2">�ϼ�
    				<input type="radio" name="bookStates" value="3">������
    			</td>
    		</tr>
    		<tr>
    			<td>����:</td>
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