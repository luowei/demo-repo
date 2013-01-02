<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pojo.CarType"%>
<%@page import="pojo.Car;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公交换乘</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 
   <form id="form1" name="form1" method="post" action="select">
     <input type="hidden" name="type" value="1"/>
  <table width="421" border="1"  align="center">
   <tr>
      <td colspan="4">
       <img src="clip_image002.jpg">
      </td>
    </tr>
    <tr>
      <td colspan="4"><label>
        起站<input type="text" name="strat" value="" />
        到站<input type="text" name="end" value="" />
        <input type="Submit" name="Submit" value="查询"/>
      </label></td>
    </tr>

    <%
    Object obj=request.getAttribute("list");
    if(obj!=null){
     List list=(List)obj;
     
     }
     %>

  </table>
</form>
  </body>
</html>
