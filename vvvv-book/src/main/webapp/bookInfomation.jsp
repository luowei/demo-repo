<%@page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.vvvv.entity.BookInfo"%>
<%//@page import="com.vvvv.entity.BookDiscuss"%>
<%//@page import="com.vvvv.entity.CustomerInfo"%>
<%//@page import="com.vvvv.dao.CustomerInfoDAO"%>
<html>
<head>
<style>
<!--
.wr{font-size: 12pt; line-height: 22px}
.wr1 {	FONT-SIZE: 12px; LINE-HEIGHT: 200%}
.wr2 {	FONT-SIZE: 14px; LINE-HEIGHT: 150%}
.wr3 {	FONT-SIZE: 12px}
.wr4 {	FONT-SIZE: 12px; LINE-HEIGHT: 150%}
// -->
</style>
<title>图书详细信息 - vvvv网上书店</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

	<jsp:include page="fgLeft.jsp"></jsp:include>
      
    <%
		BookInfo book = (BookInfo)request.getAttribute("book") ;
		//List<BookDiscuss> dis = (List<BookDiscuss>)request.getAttribute("bookDiscuss");
		//CustomerInfo customer = null;
		//CustomerInfoDAO dao = new CustomerInfoDAO();
		int size = 0;
	%>
    <td> 
      <table width="519" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td class="wr4">
            
          </td>
        </tr>
        <tr>
          <td class="wr4">
            <p><img src="images/hb11.gif" width="519" height="40"></p>
            </td>
        </tr>
        <tr>
          <td class="wr1" background="images/hb02.gif" bgcolor="#FFFBEF"> 
            <table width="500" border="0" cellspacing="0" cellpadding="5" align="center">
              <tr valign="top"> 
                <td width="160"><img src="images/<%=book.getBigImg() %>" width="150" height="219"></td>
          <td class="wr2"> 
                  <p><font color="#9C0000"><b>□ <%=book.getBookName() %>（作者：<%=book.getAuthor() %>）</b></font></p>
            <table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                      <td class="wr2"><font face="楷体_GB2312">　　<%=book.getContext() %></font></td>
                    </tr>
                    <tr> 
                      <td class="wr1">定价：<font color="#FF0000"><%=book.getPrice()%></font> 元　vvvv价：<font color="#FF0000"><%=book.getVvvvprice() %>
                        </font>元　　
                        <a href="<%=request.getContextPath()%>/GwcServlet?type=add&id=<%=book.getBookId()%>"><img src="images/xc.gif" width="15" height="13">购买</a>　
                        <a href="<%= request.getContextPath()%>/AddFavoriteServlet?bookId=<%=book.getBookId()%>"><img src="images/c.gif" width="15" height="11">收藏夹</a></td>
                    </tr>
                  </table>      <table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr valign="top"> 
                      <td width="40%" class="wr4">【作者】<%=book.getAuthor()%></td>
                      <td width="60%" class="wr4">【出版社】<%=book.getPbName() %></td>
                    </tr>
                    <tr valign="top"> 
                      <td width="60%" class="wr4">【版次】<%=book.getPbdate() %><br>
                        【ISBN号】7-220-06312-1//<br>
                        【装帧】 平装<br>
                        【库存状况】 有 </td>
                    </tr>
                  </table>
          </td>
        </tr>
      </table>
      <hr width="95%" size="1" align="center">
      <table width="95%" border="0" cellspacing="0" cellpadding="5" align="center">
        <tr>
          <td class="wr4"> 
                  <p><font color="#9C0000">【内容简介】</font><br>
              <font face="楷体_GB2312">　　<%=book.getContext()%></font>
            </p>
            </td>
        </tr>
      </table>
      <hr width="95%" size="1" align="center">
            <table width="95%" border="0" cellspacing="0" cellpadding="5" align="center">
              <tr> 
                <td class="wr1"><font color="#9C0000">【读者最新评论】</font></td>
              </tr>
              <% //if(dis.size() == 0) {%>
              		<tr> 
	                <td class="wr1"> 
	                  <p>本书还没有评论</p>
	                  </td>
	              </tr>
              <%/*} else{
            	  if(dis.size() == 1) {
              		size = 1;
            	  }else{
            		size = 2;
            	  }
            	  	for(int i = 0; i < size; i++) {
            	  		BookDiscuss di = dis.get(i);
                  		customer = dao.findById(di.getCustomerId());	
                  		*/%>
                  	<tr> 
    	                <td class="wr1"> 
    	                  <p>主题：好东西大家一起分享　　　作者：<%//=customer.getCustomerName()%>　　　发表日期：<%//=di.getWriteData()%><br>
    	                    对本书的评价：☆☆☆☆☆<br>
    	                    内容：</p>
    	                  <p align="center"><%//=di.getContext() %></p>
    	                  <hr width="99%" size="1" align="center">
    	                  </td>
    	                  
    	              </tr>
    	              
            	<%//}
            	//}%>
              <tr> 
                <td class="wr1"> 
                  <p align="center">&lt;&lt;<a href="${pageContext.request.contextPath }/ShowAllDiscussServlet?bookId=${requestScope.book.bookId}&bookName=${requestScope.book.bookName}">浏览全部评论</a> &gt;&gt;　　&lt;&lt; <a href="${pageContext.request.contextPath }/WriteDiscussServlet?bookId=${requestScope.book.bookId}&bookName=${requestScope.book.bookName}">发表评论 </a>&gt;&gt;</p>
                </td>
              </tr>
            </table>
      <hr width="95%" size="1" align="center">
          </td>
        </tr>
        <tr>
          <td class="wr4"><img src="images/hb03.gif" width="519" height="9"></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td> 
            <div align="right"><img src="images/daodu36.gif" width="593" height="58" alt="广告区"></div>
          </td>
        </tr>
      </table>
      <p align="center" class="wr1">淘书秀 | 寻书登记 | 好书快讯 | 文化沙龙 | 书店平台<br>
        关于书店 | 意见箱 | 联系我们</p>
    </td>
  </tr>
</table>
<table width="770" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="22" background="images/dd-m09.gif">&nbsp;</td>
  </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td>&nbsp;</td>
    <td class="wr1" width="10">&nbsp;</td>
    <td class="wr1">&nbsp;</td>
  </tr>
  <tr> 
    <td><a href="http://www.qybook.com"><img src="images/dd-009.gif" width="36" height="40" alt="logo" border="0"></a></td>
    <td class="wr4" width="10">&nbsp;</td>
    <td class="wr4"> 版权所有：<a href="http://www.qingyun.com" target="_blank">西安云工厂科技文化图书有限责任公司</a><br>
      地址：西安市高新一路25号创新大厦一楼　服务电话：029-8326696</td>
  </tr>
</table>
</body>
</html>
<script ></script>