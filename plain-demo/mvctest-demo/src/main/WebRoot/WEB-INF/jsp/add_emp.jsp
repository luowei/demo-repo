<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add_emp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<title>add emp</title>
  </head>
  
  <body>
  	<%--
    <form action="add.test" method="post">
    	<!-- 注意:表单字段的name值要与对应的bean中的属性的名称相同，
    	否则提交的值，不能被自动注入到对应的bean对象中。 -->
    	emp no:<input type="text" name="empNo"/><br/>
    	name:<input type="text" name="name"/><br/>
    	phone:<input type="text" name="phone"/><br/>
    	hire date:<input type="text" name="hireDate"><br/>
    	<input type="submit" value="add" />
     </form>
      --%>
      
      <form action="add_emp.test" method="post">
    	<!-- 注意:表单字段的name值要与对应的bean中的属性的名称相同，
    	否则提交的值，不能被自动注入到对应的bean对象中。 -->
    	emp no:<input type="text" name="empNo"/><br/>
    	name:<input type="text" name="name"/><br/>
    	phone:<input type="text" name="phone"/><br/>
    	hire date:<input type="text" name="hireDate"><br/>
    	department:<select name="dept">
    		<c:forEach items="${deptList}" var="dept">
    			<option value="${dept }">${dept }</option>
    		</c:forEach>
    	</select>
    	<input type="submit" value="add" />
     </form>
     
  </body>
</html>
