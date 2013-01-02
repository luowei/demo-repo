<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pojo.CarType;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Type.jsp' starting page</title>
    
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
 
   <form id="form1" name="form1" method="post" action="cartype">
     <input type="hidden" name="type" value="delete"/>
  <table width="421" border="1"  align="center">
   <tr>
      <td colspan="3">
       <img src="clip_image002.jpg">
      </td>
    </tr>
    <tr>
      <td colspan="3"><label>
        <input type="submit" name="Submit" value="删除" />
        <input type="button" name="Submit2" value="添加" onclick="window.open('TypeSave.jsp')"/>
      </label></td>
    </tr>
    <tr>
      <td width="57">选择</td>
      <td width="58">序号</td>
      <td width="217">名称</td>
      <!-- 表示JAVA页面脚本 -->
    </tr> 
    <%
    Object obj=request.getAttribute("list");
    if(obj!=null){
     List list=(List)obj;
     for(int i=0;i<list.size();i++){
     response.getWriter().print("<tr>");
     CarType type=(CarType)list.get(i);
     //点击post提交的时候 IE会将input的checkbox被选中控件数据提交到服务器
      out.print("<td><input type='checkbox' name='ids' value='"+type.getId()+"' /></td>");
      out.print("<td>"+type.getId()+"</td>");
      out.print("<td>"+type.getName()+"</td>");
      out.print("</tr>");
     }
     }
     %>

  </table>
</form>
  </body>
</html>
