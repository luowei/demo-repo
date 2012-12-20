<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 12-12-20
  Time: 下午9:22
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
    <title>保存房屋</title>
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

    <h2>保存房屋</h2>


    <form:form id="form1" modelAttribute="house"
               action="${ctx}/manage/saveHouse"
               method="post" cssClass="well form-inline" cssStyle="text-align: center">

        <label class="label">Id:
            <input name="id" class="input-large  search-query" value="${house.id}"/>
        </label><br/> <br/>

        <label class="label">房屋名:
            <form:input path="name" cssClass="input-large  search-query"/>
        </label> <br/> <br/>

        <label class="label">大小:
            <form:input path="size" cssClass="input-large  search-query"/>
        </label> <br/> <br/>

        <label class="label">状态:
            <form:input path="status" cssClass="input-large  search-query"/>
        </label> <br/> <br/>

        <label class="label">图片:
            <form:input path="img" cssClass="input-large  search-query"/>
        </label> <br/> <br/>

        <label class="label">描述:
            <form:textarea rows="3" cols="20" path="name" cssClass="input-large  search-query"/>
        </label> <br/> <br/>

        <button type="submit" class="btn btn-success">确定</button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button type="reset" class="btn btn-success">重置</button>

    </form:form>

</div>

</body>
</html>