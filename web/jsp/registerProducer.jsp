<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/12
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 导入jstl标签库。prefix：前缀 uri：jstl标签库的地址-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //写java代码，因为js底层是java

    //定义绝对路径地址
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>注册成为开发者</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/register.css"><!--登录div样式-->
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toRegister(){
        //ajax提交数据到后端
        $.ajax({
            url:"http://localhost:8080/user?method=registerProducer",
            type:"post",
            data:{
                userId:$("[name='userId']").val(),
                username:$("[name='username']").val(),
                userType:$("[name='userType']:checked").val(),//name：属性选择器；checked：过滤选择器，判断是否选中
                userCard:$("[name='userCard']").val(),
                userPhone:$("[name='userPhone']").val(),
                userAddress:$("[name='userAddress']").val(),
            },
            success:function (obj){
                if(obj){
                    alert("恭喜您成为开发者！")
                    location.href="http://localhost:8080/user?method=showGameList"
                }
                else{
                    alert("注册开发者失败！")
                }
            },
            dataType:"json"
        })
    }
    function toBack(){
        location.href="http://localhost:8080/user?method=showGameList"
    }
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
    <div id="global_main">
        <!-- 主内容题头 -->
        <div id="header_main">
            <h1 class="h1">欢迎您成为开发者</h1>
        </div>
        <!-- 主内容核心 -->
        <div id="content_main">
            <!-- table -->
            <table class="gradient-style">
                <tr>
                    <td>用户ID：</td>
                    <td>
                        <input type="text" name="userId" class="text" readonly  unselectable="on" value="${loginUser.id}">
                    </td>
                </tr>
                <tr>
                    <td>用户名：</td>
                    <td>
                        <input type="text" name="username" class="text" readonly  unselectable="on" value="${loginUser.username}">
                    </td>
                </tr>
                <tr>
                    <td>用户类型：</td>
                    <td>
                        个人<input type="radio" name="userType" value="2" checked>
                        公司<input type="radio" name="userType" value="3">
                    </td>
                </tr>
                <tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="userCard" class="text">
                    </td>
                </tr>
                <tr>
                    <td>联系方式：</td>
                    <td>
                        <input type="text" name="userPhone" class="text">
                    </td>
                </tr>
                <tr>
                    <td>联系地址：</td>
                    <td>
                        <input type="text" name="userAddress" class="text">
                    </td>
                </tr>
            </table>
        </div>
        <!-- 主内容功能区 -->
        <div id="action_main">
            <input type="button" id="space1" class="special" class="button" value="注册" onclick="toRegister()">
            <input type="button" id="space2" class="special" class="button" value="返回" onclick="toBack()">
        </div>
    </div>
<!--引入底部div-->
<%@include file="footer.jsp" %>
</html>
