<!--欢迎界面 -->
<%@ page language="java" import="java.util.*,java.sql.*,user.model.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	<!--
		function msg()
		{
			return window.confirm("你确认要删除吗？");
		}
	-->
	</script>
  </head>
  
  <body bgcolor="ff9900"> 
  	<% 
  		String u=request.getParameter("username"); 
  		//System.out.println("UserClServlet得到的username="+u);
  		//u=new String(u.getBytes("gb2312"),"iso-8859-1"); //转码 
	  	//防止用户非法登录 
  		String userName=(String)session.getAttribute("myName"); 
  		//如果用户没有登录 
  		if(userName==null) 
  		{ 
  			//返回登录界面 
  			response.sendRedirect("login.jsp?errNo=1"); 
  			return ; 
  		} 
  		 
  	 %>
    	登录成功！维唯为为恭喜你！<%=u %><br/>
    	session得到的用户名为：<%=userName %><br/>
    	<a href="login.jsp">返回重新登录</a>&nbsp;&nbsp;
    	<a href="Main.jsp?username=<%=u %>">返回主界面</a>
    	<hr>
    	<center>
    	<h1>用户信息列表</h1>
    	<%
    		//调用UserBeanCl的方法(创建一个UserBeanCl的实例，然后调用它的某个方法)，完成分布显示
   			//UserBeanCl ubc=new UserBeanCl();
   			//ArrayList al=ubc.getUserByPage(pageNow);
		   	
		   	//要显示的用户信息从request中取
		   	ArrayList al=(ArrayList)request.getAttribute("result");
		   	
		//显示
		 %>
		 <%
   	 		//得到pageCount
   	 		String s_pageCount=(String)request.getAttribute("pageCount");
   	 		int pageCount=Integer.parseInt(s_pageCount);
   	 		String s_pageSize=(String)request.getAttribute("pageSize");
   	 		int pageSize=Integer.parseInt(s_pageSize);
   	 		String s_pageNow=(String)request.getAttribute("pageNow");
   	 		int pageNow=Integer.parseInt(s_pageNow);
   	 	%>
	   	<table border="1">
	   		<tr bgcolor="pink"><td>用户id</td><td>用户名</td><td>密码</td>
	   		<td>邮箱</td><td>级别</td><td>修改用户</td><td>删除用户</td></tr>
	   	<%
	   		//定义一个颜色数组
	   		String []color={"silver","pink"};
	   		for(int i=0;i<al.size();i++)
	   		{	//从al中取出UserBean
	   			UserBean ub=(UserBean)al.get(i);
	   	 %>
	   			<tr bgcolor="<%=color[i%2] %>"><td><%=ub.getUserId() %></td><td><%=ub.getUsername() %></td>
	   			<td><%=ub.getPasswd() %></td><td><%=ub.getEmail() %></td>
	   			<td><%=ub.getGrade() %></td>
	   			<td><a href="updateUser.jsp?username=<%=u%>&pageNow=<%=pageNow %>&userId=<%=ub.getUserId()
	   				%>&userName=<%=ub.getUsername() %>&passWd=<%=ub.getPasswd() %>&email=<%=ub.getEmail() 
	   				%>&grade=<%=ub.getGrade() %>">修改用户</a></td>
	   			<td><a onclick="return msg()" href="UserClServlet?username=<%=u%>&pageNow=<%=pageNow 
	   				%>&flag=delUser&userid=<%=ub.getUserId() %>">删除用户</a></td></tr>
	   	<%
	   		}
   	 	 %>
   	 	</table>

   	 	<%	
   	 		//首页
   	 		out.println("<a href=UserClServlet?flag=fenye&pageNow=1&username="+u+">首页</a>"); //转到分页控制器处理
   	 		//上一页
   	 		if(pageNow!=1)
   	 		{
   	 			out.println("<a href=UserClServlet?flag=fenye&pageNow="+(pageNow-1)+"&username="+u+">上一页</a>");
   	 		}
   	 		//显示页面链接
   	 		for(int i=pageNow;i<pageNow+pageSize&&i<=pageCount;i++)
   	 		{
   	 			out.println("<a href=UserClServlet?flag=fenye&pageNow="+i+"&username="+u+">["+i+"]</a>");
   	 		}
   	 		//下一页
   	 		if(pageNow!=pageCount)
   	 		{
   	 			out.println("<a href=UserClServlet?flag=fenye&pageNow="+(pageNow+1)+"&username="+u+">下一页</a>");
   	 		}
   	 		//尾页
   	 		out.println("<a href=UserClServlet?flag=fenye&pageNow="+pageCount+"&username="+u+">尾页</a>&nbsp;&nbsp;");
   	 		
   	 		//跳转
   	 		out.println("<input id=\"jumpText\" type=\"text\" size=\"4\" >");
   	 		out.println("<input type=\"button\" size=\"3\" onclick=\"jump()\" value=\"跳转\">");
   	 	 %>
   	 	 <script type="text/javascript">
   	 	 	function jump(){
   	   	 	 	var pageJump=document.getElementById("jumpText");
   	   	 	 	confirm("跳转到"+pageJump.value);
				window.open("UserClServlet?flag=fenye&pageNow="+pageJump.value+"&username='<%=u%>'","_self");
   	 	 	}
   	 	 </script>
   	 	 <hr>
   	 	 <!-- 引入一张图片 -->
    	 <img src="imgs/logo.gif"/>
   	 	 </center>
  </body>
</html>
