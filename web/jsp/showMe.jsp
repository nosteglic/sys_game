<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/10
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //写java代码，因为js底层是java
    //定义绝对路径地址
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>查看个人信息</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/register.css"><!--游戏表样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toModPwd(){
        location.href="http://localhost:8080/jsp/userModPwd.jsp"
    }
    function toBack() {
        location.href="http://localhost:8080/user?method=showGameList"
    }
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<!-- 全局中心布局 -->
<div id="global_main">
    <!-- 主内容题头 -->
    <div id="header_main">
        <h1 class="h1">我的信息</h1>
    </div>
    <!-- 主内容核心 -->
    <div id="content_main">
        <!-- table -->
        <table class="gradient-style">
            <tr>
                <td>我的ID：</td>
                <td>
                    <input type="text" name="producerId" readonly  unselectable="on" value="${loginUser.id}">
                </td>
            </tr>
            <tr>
                <td>我的用户名：</td>
                <td>
                    <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.username}">
                </td>
            </tr>
            <tr>
                <td>账号类型：</td>
                <td>
                    <c:choose>
                        <c:when test="${loginUser.type == 2}">
                            <input type="text" name="producerName" readonly  unselectable="on" value="个人开发者">
                        </c:when>
                        <c:when test="${loginAdmin.type == 3}">
                            <input type="text" name="producerName" readonly  unselectable="on" value="公司开发者">
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="producerName" readonly  unselectable="on" value="用户">
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>有效证件：</td>
                <td>
                    <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.ID_number}">
                </td>
            </tr>
            <tr>
                <td>联系方式：</td>
                <td>
                    <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.phone}">
                </td>
            </tr>
            <tr>
                <td>联系地址：</td>
                <td>
                    <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.address}">
                </td>
            </tr>

        </table>

    </div>
    <!-- 主内容功能区 -->
    <div id="action_main">
        <input type="button" id="space1" class="special" value="修改密码" onclick="toModPwd()">
        <input type="button" id="space2" class="special" value="返回" onclick="toBack()">
    </div>
</div>
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
