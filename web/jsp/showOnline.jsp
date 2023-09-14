<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/12
  Time: 22:13
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
    <title>上架申请表处理界面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/show_table.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    function toPassBatch(){

    }

    function toNoPassBatch() {

    }

    function toPass(id) {
        $.ajax({
            url: "http://localhost:8080/game?method=PassPutList",
            type: "post",
            data: {put_id: id},
            success: function (obj) {
                if (obj) {
                    alert("处理操作完成！")
                    location.href = "http://localhost:8080/game?method=ShowPutList"
                }
                else {
                    alert("处理操作失败！")
                }
            },
            dataType: "json"
        })
    }

    function toNoPass(id) {
        $.ajax({
            url: "http://localhost:8080/game?method=NoPassPutList",
            type: "post",
            data: {put_id: id},
            success: function (obj) {
                if (obj) {
                    alert("处理操作完成！")
                    location.href = "http://localhost:8080/game?method=ShowPutList"
                }
                else {
                    alert("处理操作失败！")
                }
            },
            dataType: "json"
        })
    }

    //全选
    function toCheckAll(){
        //选中所有的多选框
        $(".cks").each(function (){
            this.checked = true;
        })
    }

    //全不选
    function toCheckNone(){
        //选中所有的多选框
        $(".cks").each(function (){
            this.checked = false;
        })
    }

    //反选
    function toInverse(){
        //选中所有的多选框
        $(".cks").each(function (){
            this.checked = ! this.checked;
        })
    }

    //文本就绪函数
    $(function (){
        //全选/全不选/反选，使用一个按钮完成
        $(".chkOneBtn").click(function (){
            $(".cks").each(function (){
                this.checked = ! this.checked;
            })
        })
    })
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<div class="global_main">
    <!-- 主内容题头 -->
    <div class="header_main">
        <h1 class="h1">待处理的上架申请</h1>
    </div>
    <!-- 主内容功能区 -->
    <div class="action_main">
        <table>
            <tr>
                <td><a href="http://localhost:8080/game?method=menuAdmin">返回</a></td>
            </tr>
        </table>
    </div>
    <!-- 主内容核心 -->
    <div class="content_main">
        <!-- table -->
        <table class="gradient-style">
            <tr>
                <th>选择<input type="checkbox" class="chkOneBtn"></th>
                <th>游戏名</th>
                <th>游戏类型</th>
                <th>游戏分级</th>
                <th>游戏价格</th>
                <th>游戏折扣</th>
                <th>游戏介绍</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${putList}" var="onlineGame">
                <tr align="center">
                    <td><input type="checkbox" class="cks" value="${onlineGame.put_id}"></td>
                    <!-- name与数据库无关，与实体类Student的对象需要一一匹配 -->
                    <td>${onlineGame.g_name}</td>
                    <td>${onlineGame.g_type}</td>
                    <td>${onlineGame.g_age}</td>
                    <td>${onlineGame.g_price}</td>
                    <td>${onlineGame.g_discount}</td>
                    <td>${onlineGame.g_introduction}</td>
                    <td>
                        <input type="button" value="通过" onclick="toPass(${onlineGame.put_id})">
                        <input type="button" value="不通过" onclick="toNoPass(${onlineGame.put_id})">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>