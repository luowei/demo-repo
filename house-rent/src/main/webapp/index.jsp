<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>房屋管理系统</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/bootstrap/css/bootstrap-responsive.min.css' />"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
</head>
<body>
<div class="container" style="padding-top: 60px">
    <div class="navbar navbar-fixed-top navbar-inverse">
        <div class="navbar-inner">

            <div class="container">
                <a class="brand" href="#">房屋管理系统</a>

                <ul class="nav nav-tabs">
                    <li><a href="${ctx}/manage/listUser/1">用户信息</a></li>
                    <li><a href="${ctx}/manage/listHouse/1">房屋信息</a></li>
                    <li><a href="javascript:" onclick="history.back();">返回</a></li>
                </ul>

            </div>
        </div>
    </div>


    <h1>房屋管理系统</h1>

</div>
</body>
</html>