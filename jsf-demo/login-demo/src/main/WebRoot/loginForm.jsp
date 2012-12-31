<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>My JSF 'loginForm.jsp' starting page</title>
	
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
	<f:view>
		This is my loginForm.jsp JSP page. <br>
		
		<h:form>
		<h:panelGrid columns="3">
		<h:outputLabel for="username" value="ÓÃ»§Ãû:">
		</h:outputLabel>
		<h:inputText id="username" value="#{loginBean.username}" required="true"></h:inputText>
		<h:message for="username"></h:message>
		<h:outputLabel for="password" value="ÃÜÂë:"></h:outputLabel>
		<h:inputSecret id="password" value="#{loginBean.password}" required="true">
		<f:validateLength minimum="6"></f:validateLength>
		
		</h:inputSecret>
		<h:message for="password"></h:message>
		</h:panelGrid>
		<h:panelGrid>
		<h:panelGroup>
		<h:commandButton value="µÇÂ¼" action="#{loginBean.login}"></h:commandButton>
		</h:panelGroup>
		</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>