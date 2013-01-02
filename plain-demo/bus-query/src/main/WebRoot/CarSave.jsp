<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="pojo.CarType"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TypeSave.jsp' starting page</title>
    
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
  <form id="form1" name="form1" method="post" action="car">
  <input type="hidden" name="type" value="save"/>
  <table width="335" height="67" border="1" align="center">
  <tr>
      <td colspan="2">
       <img src="clip_image002.jpg">
      </td>
    </tr>
    <tr>
      <td>公交名称</td>
      <td><input type="text" name="name" /></td>
    </tr>
     <tr>
      <td>线路类型</td>
      <td>
      <select name="typeid">
      <%
      	 Object obj=request.getAttribute("list");
      if(obj!=null){
     List list=(List)obj;
     for(int i=0;i<list.size();i++){
     response.getWriter().print("<tr>");
     CarType c=(CarType)list.get(i);
     //点击post提交的时候 IE会将input的checkbox被选中控件数据提交到服务器
      out.print("<option value='"+c.getId()+"'>"+c.getName()+"</option>");
     }
     }
       %>
      <option value=""></option>
      </select>
      </td>
    </tr>
     <tr>
      <td>公交名称</td>
      <td>
     <textarea name="remark" cols="40" rows="10"></textarea>
      </td>
    </tr>
    <tr>
      <td><input type="submit" name="Submit" value="添加" /></td>
      <td><input type="reset" name="button" value="重置" /></td>
    </tr>
  </table>
</form>
  </body>
</html>
