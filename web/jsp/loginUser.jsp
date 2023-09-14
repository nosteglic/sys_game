<%--
  Created by IntelliJ IDEA.
  User: ZOX
  Date: 2021/1/11
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入jstl标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //写java代码，因为js底层是java
    //定义绝对路径地址
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>欢迎进入登录页面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/login.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toRegister() {
        location.href="registerUser.jsp"
    }

    function toLogin() {
        var username = $("[name='username']").val()
        //属性过滤选择器
        var password = $("[name='password']").val()
        // alert("用户名："+username+"  密码："+password)
        if(username=="") {
            alert("用户名为空！")
        }
        else if(password==""){
            alert("密码为空！")
        }
        else{
            $.ajax({
                //提交到后端的地址
                url : "http://localhost:8080/user?method=loginUser",
                //提交类型
                type : "post",
                //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
                data : {
                    username : username,
                    password : password
                },
                //请求成功之后的回调函数，固定写法
                success : function (obj){//在参数中接收true
                    if(obj==0){
                        alert("用户登录成功！")
                        //location:地址跳转的方法
                        location.href="http://localhost:8080/user?method=showGameList"
                    }
                    else if(obj==1){
                        alert("用户名与密码不匹配！")
                    }
                    else{
                        alert("登录失败，有问题请联系管理员！")
                    }
                },
                //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
                dataType : "json"
            })
        }

    }

    function toBack(){
        location.href="http://localhost:8080/user?method=showGameList"
    }
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<!-- 全局中心布局 -->
    <div id="global_main">
        <!-- 左侧 -->
        <div id="content_main_left">
            <!-- 左侧登录题头 -->
            <div id="header_main">
                <h1>欢迎您的登录</h1>
            </div>
            <!-- 左侧登录内容 -->
            <div id="content_main_left_text">
                <!-- table -->
                <table class="gradient-style">
                    <tr>
                        <td>用户名：</td>
                        <td>
                            <input type="text" name="username" class="text">
                        </td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td>
                            <input type="password" name="password" class="password">
                        </td>
                    </tr>
                </table>
            </div>
            <!-- 左侧登录按钮，包括登录、返回和记住密码 -->
            <div id="content_main_left_action">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#">忘记密码？</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="special" value="登录" onclick="toLogin()">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="special" value="返回" onclick="toBack()">
            </div>
        </div>
        <!-- 右侧介绍+注册 -->
        <div id="content_main_right">
            <!-- 右侧logo -->
            <div id="content_main_right_logo">
                <img class="big_icon" src="../img/mygame.png" >
                <input type="text" value="爱玩不玩" disabled="disabled">
                <img class="small_icon" src="../img/gamehandle.png" >
            </div>
            <!-- 右侧注册按钮 -->
            <div id="content_main_right_action">
                <input type="button" class="cancel" value="加入我们" onclick="toRegister()">
            </div><!-- 右侧注册按钮 -->
        </div><!-- 右侧介绍+注册 -->
    </div><!-- 全局中心布局 -->
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
