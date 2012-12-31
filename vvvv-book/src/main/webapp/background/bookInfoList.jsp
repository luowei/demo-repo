<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@page import="com.vvvv.entity.BookInfo"%>

<html>
  <head>
    <title>图书信息列表-vvvv网上商城</title>
    <link rel="styleSheet" href="<%=request.getContextPath()%>/background/css/body.css" type="text/css" media="all" />
  </head>
  
  <body onload="init()">
    	<%
    		//共pageCount页
    		int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());
    		//第nowPage页
    		int nowPage=Integer.parseInt(request.getAttribute("nowPage").toString());
    		//pageSize 每页显示多少条
    		int pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
    		//
    		List<BookInfo> books = (List<BookInfo>)request.getAttribute("books");
    	%>
    	<table>
    		<caption>图书信息列表</caption>
    		<tr>
    			<th>bookId</th>
    			<th>bookName</th>
    			<th>author</th>
    			<th>bookTypeId</th>
    			<th>pbName</th>
    			<th>price</th>
    			<th>ygcPrice</th>
    			<th>pbDate</th>
    			<th>状态</th>
    			<th>修改</th>
    			<th>不可用</th>
    			<th>删除</th>
    		</tr>
    		<%
    		for (BookInfo book: books) {%>
    			<tr onmouseover="this.bgColor='#E6EB00'" onmouseout="this.bgColor='white'">
    				<td><%=book.getBookId()%></td>
    				<td><%=book.getBookName()%></td>
    				<td><%=book.getAuthor()%></td>
    				<td><%=book.getBooktypeId()%></td>
    				<td><%=book.getPbName()%></td>
    				<td><%=book.getPrice()%></td>
    				<td><%=book.getVvvvprice()%></td>
    				<td><%=book.getPbdate()%></td>
    				<td><%=book.getBookStatus()%></td>
    				<td><a href="FindBookInfoByIdServlet?bookId=<%=book.getBookId()%>">修改</a></td>
    				<td><a href="SetBookUnavailableServlet?bookId=<%=book.getBookId()%>">不可用</a></td>
    				<td><a href="DeleteBookInfoServlet?bookId=<%=book.getBookId()%>" 
    					title="删除图书信息" onclick="return isDelete('<%=book.getBookName() %>')">删除</a></td>
    			</tr>
    		<%}%>
    		<tr>
    			<td colspan="1" >
    				<a href="<%=request.getContextPath() %>/InitBookTypeServlet">新增</a>
    			</td>
    			<td colspan="2" >
    				<a id="unShelf" href="#" onclick=changeStatus(1)>未上架</a>&nbsp;&nbsp;
    				<a id="shelfed" href="#" onclick=changeStatus(2)>已上架</a>&nbsp;&nbsp;
    				<a id="unable" href="#" onclick=changeStatus(3)>不可用</a>&nbsp;&nbsp;
    				<a id="showAll" href="#" onclick=changeStatus(4)>所有</a>
    			</td>
    			<% //String bookStatus=request.getAttribute("bookStatus").toString(); 
    				//String pageSize=request.getAttribute("pageSize").toString();
    			%>
    			<td colspan="9" >
    				共<%=pageCount %>页 &nbsp;第<%=nowPage %>页&nbsp;&nbsp;
    				<%if(nowPage==1){%>
    					第一页&nbsp;上一页&nbsp;
    				<%} else{ %>
    					<a href="#" onclick="gotoNowPage(1)">第一页</a>
    					<a href="#" onclick="gotoNowPage(<%=nowPage-1 %>)">上一页</a>
    				
    				<%}%>
    				<%if(nowPage==pageCount){%>
    					下一页&nbsp;最后页&nbsp;
    				<%} else{ %>
    					<a href="#" onclick="gotoNowPage(<%=nowPage+1 %>)">下一页</a>
    					<a href="#" onclick="gotoNowPage(<%=pageCount %>)">最后页</a>
    				<%}%>
    				<select id="selectNowPage" onchange="gotoNowPage(this.value)">
    				<% 
    					for(int i=1;i<=pageCount;i++){%>
    						<option value=<%=i %>>第<%=i %>页</option>
    				<%}
    				%>		
    				</select>&nbsp;&nbsp;
    				<input id="pageSize" value="<%=pageSize %>" type="text" size="1"/>
    				<input type="button" onclick="gotoNowPage(<%=nowPage %>)" value="pageSize"/>
    			</td>
    		</tr>
    	</table>
  </body>
  <script type="text/javascript">
  	var bookStatus='<%=request.getAttribute("bookStatus").toString()%>';
  	function isDelete(bookName){
		return confirm("您确定要删除"+bookName+"吗？");	
  	}
  	function gotoNowPage(nowPage){
  		var pageSize=document.getElementById("pageSize").value;
		var url="<%=request.getContextPath()%>/FindAllBookInfoServlet?bookStatus="
			+bookStatus+"&nowPage="+nowPage+"&pageSize="+pageSize;
		window.location=url;
  	}
  	function changeStatus(MyStatus){
		bookStatus=MyStatus;
		gotoNowPage(1);
  	}
  	function init(){
  	  	var tag="showAll";
		if(bookStatus=='1'){
			tag="unShelf";
		}
		else if(bookStatus=='2'){
			tag="shelfed";
		}
		else if(bookStatus=='3'){
			tag="unable";
		}
		document.getElementById(tag).style.backgroundColor='yellow';
		document.getElementById("selectNowPage").value="<%=nowPage %>";//设置下接列表框被选中的部分
  	}
  </script>
</html>
