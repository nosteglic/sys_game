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
    <title>游戏详情界面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/style.css"><!--表格按钮div样式-->
<link rel="stylesheet" href="../css/details.css"><!--游戏详情界面div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toAdd(id) {
        var userId = $("[name='userId']").val()
        var gameId = id;
        //使用阿贾克斯异步提交，非常重要！！！
        //键值对形式 key : value,
        $.ajax({
            //提交到后端的地址
            url : "http://localhost:8080/user?method=addShopping",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                userId : userId,
                gameId : gameId
            },
            //请求成功之后的回调函数，固定写法
            success : function (obj){//在参数中接收true
                if (obj==0) {
                    alert("成功将物品添加到购物车！")
                    location.href = "http://localhost:8080/user?method=shoppingCart&userId="+userId
                    //这里应该跳转到当前用户id所在的购物车界面
                } else if(obj==1){
                    alert("购物车已满！")
                }
                else {
                    alert("添加物品到购物车失败，有问题请联系管理员！")
                }
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    function toShowComments(id) {
        location.href="http://localhost:8080/user?method=toSelectCommentByGameId&gameId="+id
    }
    function toComment(){
        var userId = $("[name='userId']").val()
        var gameId = $("[name='gameId']").val()
        var comment = $("[name='comment']").val()
        //测试用
        // alert(","+gameId+","+comment)

        $.ajax({
            url:"http://localhost:8080/user?method=toInsertComment",
            type:"post",
            data:{
                userId:userId,
                gameId:gameId,
                comment:comment
            },
            success:function (obj){
                if(obj){
                    alert("发表评论成功！")
                    location.href="http://localhost:8080/user?method=toSelectCommentByGameId&gameId="+gameId
                }
                else{
                    alert("发表评论失败！")
                }
            },
            dataType:"json"
        })

    }
</script>
<body>
<input type="hidden" name="userId" value="${loginUser.id}">
<!--引入头部div-->
<%@include file="header.jsp" %>
    <div id="global_main">
        <!-- 头部标题 -->
        <div id="game_header_main">
            <h1 class="h1">《${gameDetail.name}》游戏详情</h1>
        </div>
        <!-- 游戏详情表:游戏id，名称，类型，设计者等除游戏介绍外的信息显示 -->
        <div id="content_main_table">
                <table class="gradient-style">
                    <tr>
                        <td>游戏ID：</td>
                        <td>
                            <input type="text" name="gameId" readonly  unselectable="on" value="${gameDetail.id}">
                        </td>
                    </tr>
                    <tr>
                        <td>开发者名称：</td>
                        <td>
                            <input type="text" name="producerName" readonly  unselectable="on" value="${gameDetail.p_name}">
                        </td>
                    </tr>
                    <tr>
                        <td>游戏类型：</td>
                        <td>
                            <input type="text" name="gameType" readonly  unselectable="on" value="${gameDetail.type}">
                        </td>
                    </tr>
                    <tr>
                        <td>游戏分级：</td>
                        <td>
                            <input type="text" name="gameAge" readonly  unselectable="on" value="${gameDetail.age}+">
                        </td>
                    </tr>
                    <tr>
                        <td>游戏价格：</td>
                        <td>
                            <input type="text" name="gamePrice" readonly  unselectable="on" value="${gameDetail.price}">
                        </td>
                    </tr>
                    <tr>
                        <td>上架时间：</td>
                        <td>
                            <input type="text" name="gameCreateTime" readonly  unselectable="on" value="${gameDetail.time}">
                        </td>
                    </tr>
                    <tr>
                        <td>游戏介绍：</td>
                        <td><textarea rows="4" cols="50" readonly unselectable="on">${gameDetail.introduction}</textarea></td>
                    </tr>
                </table>
        </div>
        <!-- 加入购物车，返回按钮 -->
        <div id="action_main">
            <table>
                <tr>
                    <td><input type="button" class="special" value="加入购物车" onclick="toAdd(${gameDetail.id})"></td>
                    <td><a href="http://localhost:8080/user?method=showGameList">返回</a></td>
                </tr>
            </table>
        </div>
        <!-- 发表评论区 -->
        <div id="comment_content">
            <h2 class="h2">我想说</h2>
            <table class="gradient-style">
                <tr>
                    <td>用&nbsp;&nbsp;户&nbsp;&nbsp;名&nbsp;&nbsp;：</td>
                    <td>
                        <input type="text" name="userName" readonly  unselectable="on" value="${loginUser.username}">
                        <input type="button" class="special" value="发表评论" onclick="toComment()">
                    </td>
                </tr>
                <tr>
                    <td>我&nbsp;&nbsp;的&nbsp;&nbsp;评&nbsp;&nbsp;论&nbsp;&nbsp;：</td>
                    <td><textarea rows="7" cols="90" name="comment" placeholder="我想说..."></textarea></td>
                </tr>
            </table>
        </div>
        <!-- 显示其他用户的评论 -->
        <div id="comment_list">
            <h2 class="h2">评论区</h2>
            <!-- 以列表形式呈现其他用户评论 -->
            <table class="gradient-style">
                <tr>
                    <th>用户id</th>
                    <th>用户名</th>
                    <th>评论内容</th>
                    <th>评论时间</th>
                </tr>
                <c:forEach items="${commentLists}" var="commentlist">
                    <tr align="center">
                        <td>${commentlist.c_id}</td>
                        <td>${commentlist.c_name}</td>
                        <td>${commentlist.comment_content}</td>
                        <td>${commentlist.comment_time}</td>
                    </tr>
                </c:forEach>
            </table>
        </div><!-- 显示其他用户的评论 -->
    </div>
<!--引入底部div-->
<%--<%@include file="footer.jsp" %>--%>
</body>
</html>
