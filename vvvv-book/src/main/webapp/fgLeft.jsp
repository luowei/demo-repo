<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.vvvv.entity.BookType"%>
<%@page import="com.vvvv.dao.BookTypeDAO"%>
<%@page import="com.vvvv.entity.BookInfo"%>
<%//@page import="com.vvvv.gwc.Gwc"%>
<%//@page import="com.vvvv.entity.CustomerInfo"%>


<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<style type="text/css"> 
        a {text-transform:none;text-decoration:none;} 
        a:hover {text-decoration:underline} 
</style>

<table width="770" border="0" cellpadding="0" cellspacing="0" align="center">
	<%// CustomerInfo customer =  (CustomerInfo)session.getAttribute("customer"); %>
  <tr> 
    <td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="2" width="152"><a href="${pageContext.request.contextPath}/FindBookTypeServlet"><img src="<%=request.getContextPath()%>/images/logo.gif" width="152" height="84" alt="云工厂网上书店" border="0"></a></td>
          <td height="53" width="62"><img src="${pageContext.request.contextPath}/images/dd00-h.gif" width="62" height="53"></td>
          <td height="53"><a href="BookInfomation.htm"><img src="${pageContext.request.contextPath}/images/daodu34.gif" alt="广告区" width="281" height="44" border="0"></a></td>
          <td>
          <table border="0"  cellpadding="0" cellspacing="0">
          <tr>
          <td valign="top" height="53" width="50"> 
            <div align="center"> <a href="${pageContext.request.contextPath}/gwc.jsp"><img src="images/daodu03.gif" width="27" height="25" alt="购物车"></a><br>
              <span class="wr1"><a href="${pageContext.request.contextPath}/gwc.jsp">购物车</a></span></div>
          </td>
          <td valign="top" height="53" width="65"> 
            <div align="center">
            <a href="${pageContext.request.contextPath}/ShowMyOrderServlet"><img src="<%=request.getContextPath()%>/images/daodu02.gif" width="28" height="25" alt="订单查询"><br>
              <span class="wr1">订单查询</span></a></div>
          </td>
          <td valign="top" height="53" width="65"> 
            <div align="center">
              <a href="${pageContext.request.contextPath}/FgExitServlet"><img src="${pageContext.request.contextPath}/images/daodu04.gif" width="26" height="25" alt="安全退出"><br>
              <span class="wr1">安全退出</span></a>
            </div>
          </td>
          <td valign="top" height="53" width="65"> 
            <div align="center">
            <a href="${pageContext.request.contextPath}/ShowMyFavoriteServlet"><img src="${pageContext.request.contextPath}/images/dd-10.gif" width="27" height="25" alt="我的帐户"><br>
              <span class="wr1">收藏夹</span></a></div>
          </td>
          </tr>
          <tr>
          <td colspan="4">
		    <%// if(customer != null) {%>
		    	&nbsp;&nbsp;&nbsp;&nbsp;<b>【<font color="blue"><%//=customer.getCustomerName()%></font>】&nbsp;&nbsp;<font color="green">欢迎你回来！！</font></b>
		    <%//} else{%><br>
		        &nbsp;&nbsp;&nbsp;&nbsp;<font color="red">您还没有登录请点击<a href="<%= request.getContextPath() %>/egou/login.jsp"><b>这里</b></a>登陆</font>！
		    <%//}%>
		  </td>
		  </tr>
		  </table>
		  </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
  	<td>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr> 
	    <td nowrap>
	    <a style="text-decoration: none" href="${pageContext.request.contextPath}/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu05.gif" width="106" height="31" alt="首页" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu06.gif" width="106" height="31" alt="畅销" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu07.gif" width="106" height="31" alt="文学" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu08.gif" width="144" height="31" alt="vvvv书系" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu09.gif" width="106" height="31" alt="文化" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu10.gif" width="106" height="31" alt="经管" border="0">
	    </a>
	    </td>
	    <td nowrap>
	    <a style="text-decoration: none" href="<%=request.getContextPath() %>/FindBookTypeServlet">
	    <img src="<%=request.getContextPath()%>/images/daodu11.gif" width="106" height="31" alt="少儿" border="0">
	    </a>
	    </td>
	    </tr>
	    </table>
	  </td>
  </tr>
</table>
<table width="770" border="0" cellpadding="0" cellspacing="0" align="center" height="28">
  <tr>
    <td background="<%=request.getContextPath()%>/images/daodu12.gif" class="wr1" width="147"> 
      <div align="center"><a href="${pageContext.request.contextPath}/FindBookTypeServlet"><font color="#FFFFFF" face="Arial, Helvetica, sans-serif"><b>www.xaygc.com</b></font></a></div>
    </td>
    <td background="<%=request.getContextPath()%>/images/daodu12.gif" valign="bottom" class="wr1" width="415"> 
      <div align="right"><font color="#FFFFFF"><b>今日上架 | 专题特区 | 排行榜 | 书店平台 | 购书演示</b></font></div>
    </td>
    <td background="<%=request.getContextPath()%>/images/daodu12.gif" valign="bottom" width="58">&nbsp;</td>
    <td background="<%=request.getContextPath()%>/images/daodu12.gif" valign="bottom" width="150"><font color="#FFFFFF"><img src="<%=request.getContextPath()%>/images/daodu14.gif" width="96" height="21" alt="好书快讯"></font></td>
  </tr>
</table>
<table width="770" border="0" cellpadding="0" align="center" cellspacing="0">
  <tr><td valign="top"><br></td></tr><tr valign="top"> 
    <td width="170"> 
      <p>
      <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr> 
        <td width="9"><img src="images/a1.gif" width="10" height="22"></td>
        <td bgcolor="#8BB083"> 
          <div align="center"><img src="images/wenhua06.gif" width="40" height="22"><img src="images/wenhua05.gif" width="67" height="22" alt="购物车"></div>
        </td>
        <td width="9" align="right"><img src="images/a2.gif" width="10" height="22"></td>
      </tr>
    	</table>
      <%// Gwc gwc = (Gwc)session.getAttribute("gwc"); 
      	//if(gwc != null && gwc.getItems().size() != 0) {%>
	      	
      		<table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
	        <tr> 
	          <td background="images/a3.gif" class="wr1"> 
	            <table width="100%" border="0" cellspacing="0" cellpadding="5">
	              <tr>
	                <td class="wr4">有 <font color="#FF0000"><%//=gwc.getItems().size()%></font> 种商品在购物车内<br>
	                  总计：￥ <font color="#FF0000"><%//=gwc.getSum()[1]%></font> 元<br>
	                  <a href="<%= request.getContextPath()%>/gwc.jsp">查看购物车清单/结帐</a>&gt;&gt;&gt;<br>
	                </td>
	              </tr>
	            </table>
	          </td>
	        </tr>
	        <tr> 
	          <td valign="top" height="20"><img src="images/a4.gif" width="153" height="6"></td>
	        </tr>
	      </table>
	      	
      <%//} else {%>
    	  <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
	        <tr> 
	          <td background="images/a3.gif" class="wr1"> 
	            <table width="100%" border="0" cellspacing="0" cellpadding="5">
	              <tr>
	                <td class="wr4"><font color="#FF0000">购物车内没有商品，请您选购商品</font></td>
	              </tr>
	            </table>
	          </td>
	        </tr>
	        <tr> 
	          <td valign="top" height="20"><img src="images/a4.gif" width="153" height="6"></td>
	        </tr>
	      </table>
      <%//}%>
      <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td width="9"><img src="images/a1.gif" width="10" height="22"></td>
          <td bgcolor="#8BB083" class="wr2"> 
            <div align="center"><b><font color="#AC1000">[图书搜索]</font></b> </div>
          </td>
          <td width="9" align="right"><img src="images/a2.gif" width="10" height="22"></td>
        </tr>
      </table>
      <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td background="images/a3.gif" class="wr1">
          <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr> 
		          <td class="wr4">
		         	<form name="findForm" action="<%=request.getContextPath()%>/FindBookInfoByIndexServlet?nowPage=1" method="post">
		         	书名：<input type="text" size=10 name="bookName"><br>	
		         	作者：<input type="text" size=10 name="author"><br><br>
		         	&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/changxiao09.gif"  onclick="findForm.submit()" width="45" height="18" alt="搜索">
		         	<img src="images/reset.png"  onclick="findForm.reset()" width="45" height="18" alt="重置"><br>
		         	</form>
		         </td>
		      </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td valign="top" height="20"><img src="images/a4.gif" width="153" height="6"></td>
        </tr>
      </table>
      <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
        	
          <td width="10"><img src="<%=request.getContextPath()%>/images/dd-m2.gif" width="10" height="22"></td>
          <td bgcolor="#3AA6BF"> 
            <div align="center"><font color="#FFF1C9" class="wr3"><b><font color="#FDF6DF">会员登录</font></b></font></div>
          </td>
          <td width="10" align="right"><img src="<%=request.getContextPath()%>/images/dd-m3.gif" width="10" height="22"></td>
        </tr>
      </table>
      <table width="153" border="0" cellspacing="0" cellpadding="0" align="center">
      	
        <tr> 
        
          <td background="<%=request.getContextPath()%>/images/dd-m6.gif" class="wr1" valign="bottom"> 
            <div align="center">
            <br>
            <a href="egou/login.jsp"><img border="0" src="<%=request.getContextPath()%>/images/daodu18.gif" width="55" height="21" alt="登录"></a>　
            <a href="egou/register.jsp"><img border="0" src="<%=request.getContextPath()%>/images/dd-m1.gif" width="76" height="21" alt="新会员注册"></a></div>
          </td>
        </tr>
        <tr> 
          <td valign="top" height="20"><img src="<%=request.getContextPath()%>/images/dd-m4.gif" width="151" height="8"></td>
        </tr>
      </table>
      <%
      	
		List<BookType> bookTypes = (List<BookType>)request.getAttribute("bookTypes");
		BookTypeDAO dao = new BookTypeDAO();
		%>
      <table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr>
          <td bgcolor="#3AA6BF" colspan="2"> 
            <div align="center"><img src="<%=request.getContextPath()%>/images/dd-m17.gif" width="82" height="23" alt="分类浏览"></div>
          </td>
        </tr>
         <%// for (BookType bookType: bookTypes) {
         	for(int i=0;i<8;i++)
         	{
         		BookType bookType=bookTypes.get(i);
         	%>
        <tr> 
	       	<td bgcolor="#F5F2E3" class="wr1" colspan="2"> 
	       		<p>
	       		<b><a href="<%=request.getContextPath()%>/ShowTitleOneServlet?bookTypeId=<%=bookType.getBookTypeId()%>&typeOne=<%=bookType.getBookTypeName()%>">
	       		<%=bookType.getBookTypeName()%>:
	       		</a></b>
		       		<%List<BookType> types = dao.findAll(bookType.getBookTypeId());
						for (BookType type : types) {%>
							<a href="<%=request.getContextPath()%>/FindBookInfoByBookTypeServlet?bookTypeId=<%=type.getBookTypeId()%>">
							<%=type.getBookTypeName()%>|
							</a>
					<%}%>
	       		<br>
	       		</p>
	       	</td>
        </tr>
        <%}%>
        <tr> 
          <td bgcolor="#3AA6BF" colspan="2"> 
            <div align="center"><img src="<%=request.getContextPath()%>/images/dd-m15.gif" width="81" height="21" alt="更多书店"></div>
          </td>
        </tr>
        <tr> 
          <td> 
            <div align="right">
              <input type="text" name="textfield4" value="书店检索" size="15">
            </div>
          </td>
          <td width="37"><img src="<%=request.getContextPath()%>/images/dd-m08.gif" width="37" height="21" alt="检索"></td>
        </tr>
      </table>
      <table width="150" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.wfjsd.com/" target="_blank">北京新华书店王府井书店</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.winxuan.com/" target="_blank">文轩网网上书店</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.china-pub.com/" target="_blank">china-pub网上书店</a> </b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.21zd.com/" target="_blank">新华书店</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td class="wr1">
            <div align="center"><font color="#68A3A1"><b><a href="http://www.amazon.cn" target="_blank">卓越网上书店</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.360buy.com" target="_blank">音乐天地图书</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/point.gif" width="1" height="1"></td>
        </tr>
		<tr>
          <td>
            <div align="center" class="wr1"><font color="#68A3A1"><b><a href="http://www.kongfz.com/" target="_blank">孔夫子旧书店</a></b></font></div>
          </td>
        </tr>
        <tr>
          <td height="1" bgcolor="#000000"><img src="<%=request.getContextPath()%>/images/point.gif" width="1" height="1"></td>
        </tr>
      </table>
      <p align="center" class="wr1"><img src="<%=request.getContextPath()%>/images/3297030.gif" width="100" height="35" alt="书店LOGO"></p><hr>
      <p align="center" class="wr1"><img src="<%=request.getContextPath()%>/images/3297036.gif" width="100" height="35" alt="书店LOGO"></p><hr>
      <p align="center" class="wr1"><img src="<%=request.getContextPath()%>/images/3297038.gif" width="100" height="35" alt="书店LOGO"></p><hr>
      <p align="center" class="wr1"><img src="<%=request.getContextPath()%>/images/3297041.gif" width="100" height="35" alt="书店LOGO"></p><hr>
    </td>