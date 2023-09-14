<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/10
  Time: 12:49
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
    <title>注册界面</title>

</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/register.css"><!--登录div样式-->
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toRegister(){
        var username = $("[name='username']").val()
        var password = $("[name='password']").val()
        var passwordConfirm = $("[name='passwordConfirm']").val()

        if(username==""||password==""){
            alert("用户名或密码不能为空！")
        }
        else {
            if(password == passwordConfirm){
                $.ajax({
                    //提交到后端的地址
                    url : "http://localhost:8080/user?method=registerUser",
                    //提交到后端，实现注册功能
                    //提交的类型
                    type : "post",
                    //提交到后端的数据
                    data : {
                        //属性过滤选择器
                        username:$("[name='username']").val(),
                        password:$("[name='password']").val(),
                    },
                    //回调函数，obj接收参数
                    success : function (obj){
                        if(obj == 0){
                            alert("用户注册成功！");
                            location.href="http://localhost:8080/jsp/loginUser.jsp"
                        }
                        else if(obj == 1){
                            alert("用户名已存在！")
                        }
                        else{
                            alert("注册失败！有问题请联系管理员！！！")
                        }
                    },
                    //返回的数据类型
                    dataType : "json"
                })
            }
            else{
                alert("两次输入的密码不一致！")
            }
        }
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
            <h1 class="h1">创建您的账户</h1>
        </div>
        <!-- 主内容核心 -->
        <div id="content_main">
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
                <tr>
                    <td>确认密码：</td>
                    <td>
                        <input type="password" name="passwordConfirm" class="password">
                    </td>
                </tr>
            </table>
        </div>
        <!-- 主内容功能区 -->
        <div id="action_main">
            <input type="button" id="space1" class="special" value="注册" onclick="toRegister()">
            <input type="button" id="space2" class="special" value="返回" onclick="toBack()">
        </div>
    </div>
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
