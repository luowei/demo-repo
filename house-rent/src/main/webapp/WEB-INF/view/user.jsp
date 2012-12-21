<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 12-12-20
  Time: 下午7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>用户信息</title>
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
    <c:if test="${not empty message or not empty error}">
        <div class="messagePlace" style="padding-top: 60px">
            <c:if test="${not empty message}">
                <div id="message" class="success" style="text-align: center;">${message}</div>
            </c:if>

            <c:if test="${not empty error}">
                <div id="errorMsg" class="error" style="text-align: center;">${error}</div>
            </c:if>
        </div>
    </c:if>

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

    <h2>用户列表</h2>

    &nbsp;&nbsp;&nbsp;&nbsp;
    <a type="button" class="btn btn-success" href="${ctx}/manage/toAddUser">添加</a><br/>
    <hr/> <br/>

    <table class="table table-hover table-bordered table-striped table-condensed">
        <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>用户类型</th>
            <th>修改</th>
            <th>删除</th>
        </tr>

        <c:forEach items="${userPage.content}" var="user" varStatus="st">
            <tr <c:if test="${st.index%2 eq 1}">class="warning" </c:if>>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.type}</td>
                <td>
                    <a type="button" class="btn btn-success" href="${ctx}/manage/buy/toUpdateuser/${user.id}">修改</a>
                </td>
                <td>
                    <a type="button" class="btn btn-success" href="${ctx}/manage/buy/deluser/${user.id}">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <div class="pagination-centered">
        <jsp:include page="pagination.jsp"/>
    </div>

</div>

</body>
</html>