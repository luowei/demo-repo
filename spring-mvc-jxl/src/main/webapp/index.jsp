<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.4.min.js"></script>
    <%@ include file="resources/common/meta.jsp"%>
</head>
<script type="text/javascript">
    $(document).ready(function(){
        $("#exec").click(function(){
            //获取下拉框的值
            var titlesValue = "";//$("#columns").find("option:selected").text();
            $("#columns").find("option:selected").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出
                titlesValue += ($(this).text())+",";
            });
            var names = 	$("#columns").val();
            $("#colums").val(names);
            $("#titles").val(titlesValue);
        });
    });

</script>
<body>&nbsp;&nbsp; &nbsp;
<div style="border: 1px solid #ccc; width: 50%;height:200px;align:center;margin-top:200px;margin-left:300px;padding:50px;">
    <form action="${pageContext.request.contextPath}/view/excel" method="post">
        <input type="submit" value="使用POI导出Excel"><br>
    </form>
    <hr><br>
    <form method="post" action="${pageContext.request.contextPath}/view/jxlExcel">
        <select id="columns" multiple="multiple" style="width:100px;height:120px;">
            <option value="id">ID</option>
            <option value="name">姓名</option>
            <option value="sex">性别</option>
            <option value="age">年龄</option>
            <option value="password">密码</option>
            <option value="address">地址</option>
        </select>
        <input type="hidden" id="titles" name="titles">
        <input type="hidden" id="colums" name="colums">
        <input type="submit" id="exec" value="使用JXL导出Excel"><br>
    </form>
    <hr><br>
    <form action="${pageContext.request.contextPath}/view/pdf" method="post">
        <input type="submit" value="导出PDF"><br>
        <br>
        <img src="${pageContext.request.contextPath}/img/car" width="100px" height="50px"/>
    </form>
</div>
</body>
</html>
