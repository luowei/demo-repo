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
    
    <title>公交站点查询</title>
    
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
     <input type="hidden" name="type" value="2"/>
  <table width="421" border="1"  align="center">
   <tr>
      <td colspan="4">
       <img src="clip_image002.jpg">
      </td>
    </tr>
    <tr>
      <td colspan="4"><label>
        <input type="text" name="name" value="" />
        <input type="Submit" name="Submit" value="查询"/>
      </label></td>
    </tr>

    <%
    Object obj=request.getAttribute("list");
    if(obj!=null){
     List list=(List)obj;
     for(int i=0;i<list.size();i++){
     response.getWriter().print("<tr>");
     Car c=(Car)list.get(i);
     //点击post提交的时候 IE会将input的checkbox被选中控件数据提交到服务器

      out.print("<td colspan='4'>公车名称:  "+c.getName()+"</td>");
      out.print("</tr>");
      response.getWriter().print("<tr>");
      if(c.getCartype().getName()!=null){
      out.print("<td colspan='4' >公车所属线路:"+c.getCartype().getName()+"</td>");
      }
      else{
      out.print("<td colspan='4'>公车所属线路:暂时没有选择路线类型</td>");
      }
      
      out.print("</tr>");
      
      response.getWriter().print("<tr>");
       out.print("<td colspan='4'>去程:  "+c.getRemark()+"</td>");
       out.print("</tr>");
       String d[]=c.getRemark().split(",");
       String text="";
       for(int k=d.length-1;k>=0;k--){
       if(k==0){
          text+=d[k];
       }
       else{
          text+=d[k]+",";
       }
     
       }
       response.getWriter().print("<tr>");
       out.print("<td colspan='4'>回程:  "+text+"</td>");
       out.print("</tr>");
     }
     }
     %>

  </table>
</form>
  </body>
</html>
