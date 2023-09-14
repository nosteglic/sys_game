<%--
  Created by IntelliJ IDEA.
  User: ZOX
  Date: 2021/1/11
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入jstl标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8"/>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/header.css"/>
<%--引入jquery控件--%>
<script src="../jquery/jquery-1.7.2.js"></script>
<script>
    function deleteUser(){
        if (confirm("确认要注销当前用户吗？")){
            $.ajax({
                //提交到后端的地址
                url : "http://localhost:8080/user?method=logout",
                //提交到后端，实现注册功能
                //提交的类型
                type : "post",
                //提交到后端的数据
                data : {
                    //属性过滤选择器
                    id:${loginUser.id},
                },
                //回调函数，obj接收参数
                success : function (obj){
                    if(obj==0){
                        alert("注销用户成功！");
                        location.href="http://localhost:8080/user?method=showGameList";
                    }
                    else{
                        alert("注销用户失败！有问题请联系管理员！！！")
                    }
                },
                //返回的数据类型
                dataType : "json"
            })
        }
    }
</script>
<!--右上角登录框DIV-->
<div id="login_bar" align="right" >
    <c:choose>
        <c:when test="${loginUser.username != null}"><!--用户-->
            <c:choose>
                <c:when test="${loginUser.type == 1}"><!--普通用户-->
                    <a href="" >欢迎普通用户&nbsp;&nbsp;${loginUser.username}&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;|</a>
                    <a href="http://localhost:8080/jsp/registerProducer.jsp" >&nbsp;&nbsp;&nbsp;成为开发者</a>
                </c:when>
                <c:when test="${loginUser.type == 2}"><!--个人开发者-->
                    <a href="" >欢迎个人开发者用户&nbsp;&nbsp;${loginUser.username}&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;|</a>
                    <a href="http://localhost:8080/user?method=exit">&nbsp;&nbsp;&nbsp;退出登录</a>
                </c:when>
                <c:when test="${loginUser.type == 3}"><!--公司开发者-->
                    <a href="" >欢迎公司开发者用户&nbsp;&nbsp;${loginUser.username}&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;|</a>
                    <a href="http://localhost:8080/user?method=exit">&nbsp;&nbsp;&nbsp;退出登录</a>
                </c:when>
            </c:choose>
        </c:when>
        <c:when test="${loginAdmin.username != null}"><!--管理员-->
            <a href="" >欢迎管理员&nbsp;&nbsp;${loginAdmin.username}&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;|</a>
            <a href="http://localhost:8080/game?method=exit">&nbsp;&nbsp;&nbsp;退出登录</a>
        </c:when>
        <c:otherwise>
            <a href="http://localhost:8080/jsp/loginUser.jsp" >用户登录&nbsp;&nbsp;&nbsp;|</a>
            <a href="http://localhost:8080/jsp/loginAdmin.jsp" >&nbsp;&nbsp;&nbsp;管理员登录</a>
        </c:otherwise>
    </c:choose>
</div><!--close login_bar div-->
<!--图标+功能栏DIV-->
<div id="header-wrapper">
    <div id="header" class="container">
        <div id="logo" align="left">
            <img src="../img/mygame.png" >
            <h1><a href="#">MyGame</a></h1>
        </div><!--close logo div-->
        <div id="menu">
            <ul><!--一级菜单-->
                <c:choose>
                    <c:when test="${loginUser.username != null}"><!--用户-->
                        <li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'"><a href="http://localhost:8080/user?method=showGameList">游戏商店</a></li>
                        <li class="menu2" onmouseover="this.className='menu1'" onmouseout="this.className='menu2'"><a href="http://localhost:8080/user?method=shoppingCart&userId=${loginUser.id}">购物车</a></li>
                        <li class="menu2" onmouseover="this.className='menu1'" onmouseout="this.className='menu2'"><a href="">个人设置</a>
                            <div class="list"><!--二级菜单-->
                                <a href="http://localhost:8080/jsp/showMe.jsp">查看个人信息</a><br/>
                                <a href="http://localhost:8080/jsp/userModPwd.jsp">修改密码</a><br/>
                                <a href="http://localhost:8080/user?method=exit">退出登录</a><br/>
                                <a href="" onclick="deleteUser()">注销用户</a><br/>
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${loginAdmin.username != null}"><!--管理员-->
                        <li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'"><a href="http://localhost:8080/jsp/menuAdmin.jsp">管理员管理</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'"><a href="http://localhost:8080/user?method=showGameList">游戏商店</a></li>
                        <li class="menu2" onmouseover="this.className='menu1'" onmouseout="this.className='menu2'"><a href="http://localhost:8080/user?method=shoppingCart">购物车</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!--close menu div-->
    </div><!--close header div-->
</div>