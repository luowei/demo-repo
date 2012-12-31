<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk"%>
<html>
  <head>
    <title>消息提示页面-vvvv网上商城</title>
<link rel="styleSheet" href="<%=request.getContextPath()%>/background/css/body.css" type="text/css" media="all" />
  </head>
  
  <body>
<br><br>	   
	   <p align="center">
	   	<font color="red" size="12"><%=request.getAttribute("msg")%><br><br>
	   	<a href="<%=request.getContextPath()%><%=request.getAttribute("returnPath")%>">返回</a>
	   	</font>
	   </p>
</body>
</html>
