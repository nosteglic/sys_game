<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/13
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 导入jstl标签库。prefix：前缀 uri：jstl标签库的地址-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>我开发的游戏</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/show_table.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toModify(gameId){
        $.ajax({
            url:"http://localhost:8080/user?method=chsOneMyGame",
            type:"post",
            data:{
                gameId:gameId

            },
            success:function (obj){
                location.href="http://localhost:8080/jsp/gameModify.jsp"
            },
            dataType:"json"
        })
    }

    function toOffline(gameId) {
        $.ajax({
            url:"http://localhost:8080/user?method=chsOneMyGame",
            type:"post",
            data:{
                gameId:gameId

            },
            success:function (obj){
                location.href="http://localhost:8080/jsp/gameOffline.jsp"
            },
            dataType:"json"
        })
    }
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<div class="global_main">
    <!-- 主内容题头 -->
    <div class="header_main">
        <h1 class="h1">我开发的游戏</h1>
    </div>
    <!-- 主内容功能区 -->
    <div class="action_main">
        <table>
            <tr>
                <td><a href="http://localhost:8080/jsp/gameOnline.jsp">上架游戏</a></td>
                <td><a href="http://localhost:8080/user?method=showGameList">返回</a></td>
            </tr>
        </table>
    </div>
    <!-- 主内容核心 -->
    <div class="content_main">
        <!-- table -->
        <table class="gradient-style">
            <tr>
                <th>游戏ID</th>
                <th>游戏名</th>
                <th>游戏类型</th>
                <th>游戏分级</th>
                <th>游戏价格</th>
                <th>游戏折扣</th>
                <th>游戏介绍</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${onlineGames}" var="onlineGame">
                <tr align="center">
                    <td>${onlineGame.id}</td>
                    <td>${onlineGame.name}</td>
                    <td>${onlineGame.type}</td>
                    <td>${onlineGame.age}</td>
                    <td>${onlineGame.price}</td>
                    <td>${onlineGame.discount}</td>
                    <td>${onlineGame.introduction}</td>
                    <td>
                        <input type="button" value="修改" onclick="toModify(${onlineGame.id})">
                        <input type="button" value="下架" onclick="toOffline(${onlineGame.id})">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
