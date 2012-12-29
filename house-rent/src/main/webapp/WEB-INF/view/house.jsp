<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 12-12-20
  Time: 下午7:42
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
    <title>房屋信息</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>"/>
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

    <h2>房屋列表</h2>


    <form:form id="form1" modelAttribute="house"
               action="${ctx}/manage/listHouse/1"
               method="post" cssClass="well form-inline">

        <label class="label">房屋名:
            <form:input path="name" cssClass="input-large search-query"/>
        </label>

        <button type="submit" class="btn btn-success">
            <i class="icon-search icon-white"></i>搜索
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a type="button" class="btn btn-success" href="${ctx}/manage/toAddHouse">添加</a><br/>
        <hr/>

        <div class="pagination-centered">
            <jsp:include page="pagination.jsp"/>
        </div>

        <table class="table table-hover table-bordered table-striped table-condensed">
            <tr>
                <th>Id</th>
                <th>房屋名</th>
                <th>大小</th>
                <th>状态(T)</th>
                <th>出售者</th>
                <th>购买者</th>
                <th>中介</th>
                    <%--<th>描述</th>--%>
                <th>图片</th>
                <th>购买</th>
                <th>修改</th>
                <th>删除</th>
            </tr>

            <c:forEach items="${housePage.content}" var="house" varStatus="st">
                <tr <c:if test="${st.index%2 eq 1}">class="warning" </c:if>>
                    <td>${house.id}</td>
                    <td>${house.name}</td>
                    <td>${house.size}</td>
                    <td>${house.status}</td>
                    <td>${house.seller}</td>
                    <td>${house.buyer}</td>
                    <td>${house.middler}</td>
                        <%--<td>${house.desc}</td>--%>
                    <td><img src="${house.img}" height="100px" width="100px"></td>
                    <td>
                            <%--<a type="button" class="btn btn-success" href="${ctx}/manage/buyHouse/${house.id}">购买</a>--%>
                        <a type="button" class="btn btn-success" href="#">购买</a>
                    </td>
                    <td>
                        <a type="button" class="btn btn-success" href="${ctx}/manage/toUpdateHouse/${house.id}">修改</a>
                    </td>
                    <td>
                        <a type="button" class="btn btn-success" href="${ctx}/manage/delHouse/${house.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <div class="pagination-centered">
            <jsp:include page="pagination.jsp"/>
        </div>

    </form:form>

</div>

</body>
</html>