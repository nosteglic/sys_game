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
    <title>用户界面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/menu.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    function findPage(i) {
        location = "http://localhost:8080/user?method=showGameList&current=" + i;
    }
    function toView(id) {
        var userId = $("[name='checkLogin']").val()
        if(userId == ""){
            alert("请先登录！")
            location.href="http://localhost:8080/jsp/loginUser.jsp"
        }else {
            location.href = "http://localhost:8080/user?method=showGameDetailById&gameId=" + id
        }
    }

    function toQueryLikeName(){
        var queryLikeName = $("[name='queryLikeName']").val()
        // alert("测试搜索内容："+queryByName)
        $.ajax({
            //提交到后端的地址：根据游戏名称进行搜索
            url : "http://localhost:8080/user?method=searchLikeName",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                queryLikeName:queryLikeName
            },
            //请求成功之后的回调函数，固定写法
            success : function (){//在参数中接收true
                location.href="http://localhost:8080/jsp/menuUser.jsp"
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    function toAdd(id) {
        var userId = $("[name='checkLogin']").val()
        if(userId == ""){
            alert("请先登录！")
            location.href="http://localhost:8080/jsp/loginUser.jsp"
        }else {
            var gameId = id
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
                    if (obj==1) {
                        alert("成功将物品添加到购物车！")
                        location.href = "http://localhost:8080/user?method=shoppingCart&userId="+userId
                        //这里应该跳转到当前用户id所在的购物车界面
                    }else if(obj==2){
                        alert("购物车已存在，添加物品到购物车失败！！")
                    }else if(obj==3){
                        alert("购物车已满！！")
                    }else{
                        alert("加入失败，请联系管理员！！")
                    }
                },
                //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
                dataType : "json"
            })
        }
    }
</script>
<body>
<input type="hidden" name="checkLogin" value="${loginUser.id}">
<!--引入头部div-->
<%@include file="header.jsp" %>
    <!-- 全局中心布局 -->
    <div id="global_main">
        <!-- 左侧logo+分类+开发者 -->
        <div id="content_main_left">
            <!-- 左侧logo -->
            <div id="content_main_left_logo">
                <img src="../img/classfiy.png" alt="游戏分类">
            </div>
            <!-- 左侧分类栏 -->
            <div id="content_main_left_class">
                <h2 class="h2">游戏分类</h2>
                <dl>
                    <dt><h4 class="h4">&nbsp;&nbsp;按类型浏览</h4></dt>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=">显示所有</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=休闲">休闲</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=体育">体育</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=冒险">冒险</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=动作">动作</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=联机">联机</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=模拟">模拟</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=独立">独立</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=竞技">竞技</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=策略">策略</a></dd>
                    <dd><a href="http://localhost:8080/user?method=searchByType&gameType=角色扮演">角色扮演</a></dd>
                </dl>
            </div>
            <!-- 左侧开发者功能-->
            <div id="producer_action">
                <img src="../img/producer.png" alt="开发者">
                <h2 class="h2">开发者功能</h2><br>
                <c:choose>
                    <c:when test="${loginUser.type ==2}"><!--当是开发者时-->
                        <a href="http://localhost:8080/user?method=shoppingCart&userId=${loginUser.id}">查看购物车</a><br><br>
                        <a href="http://localhost:8080/user?method=selectOnlineGame&userId=${loginUser.id}">查看开发的游戏</a><br><br>
                        <a href="http://localhost:8080/jsp/gameOnline.jsp">上架游戏</a><br><br>
                    </c:when>
                    <c:when test="${loginUser.type ==3}"><!--当是开发者时-->
                        <a href="http://localhost:8080/user?method=shoppingCart&userId=${loginUser.id}">查看购物车</a><br><br>
                        <a href="http://localhost:8080/user?method=selectOnlineGame&userId=${loginUser.id}">查看开发的游戏</a><br><br>
                        <a href="http://localhost:8080/jsp/gameOnline.jsp">上架游戏</a><br><br>
                    </c:when>
                    <c:otherwise><!--当不是开发者时-->
                        <label>[您还不是我们的开发者]<br><br>欢迎加入我们的开发者!<br>体验更多精彩的功能！</label>
                    </c:otherwise>
                </c:choose>
            </div>
        </div><!-- 左侧logo+分类+开发者 -->
        <!-- 右侧搜索+游戏显示 -->
        <div id="content_main_right">
            <!-- 右侧搜索框 -->
            <div id="content_main_right_query">
                <input type="text" name="queryLikeName" placeholder="请输入搜索的游戏名称...";>
                <input type="button" class="special" value="搜索" onclick="toQueryLikeName()"><!-- 以后会改成图片 -->
            </div>
            <!-- 右侧游戏列表标题 -->
            <div id="header_main">
                <h1 class="h1">游戏列表</h1>
            </div>
            <!-- 右侧游戏列表 -->
            <div id="game_list_main">
                <table class="gradient-style">
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>类型</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${games}" var="game">
                        <tr align="center">
                            <!-- name与数据库无关，与实体类Student的对象需要一一匹配 -->
                            <td>${game.id}</td>
                            <td>${game.name}</td>
                            <td>${game.type}</td>
                            <td>${game.price}</td>
                            <td>
                                <input type="button" class="button" value="查看" onclick="toView(${game.id})">
                                <input type="button" class="button" value="加入购物车" onclick="toAdd(${game.id})">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="page_style">
                <label>共&nbsp;&nbsp;<i>${page.rows}</i>&nbsp;&nbsp;条记录</label>

                <!-- 第一页-->
                <p>  <c:if test="${page.current <= 1 }"></c:if>
                    <a href="javascript:;" <c:if test="${page.current > 1 }">onclick="findPage(1)"</c:if> >首页</a>

                    <!-- 上一页 -->
                    <c:if test="${page.current <= 1 }"></c:if>
                    <a href="javascript:;" <c:if test="${page.current > 1 }">onclick="findPage('${page.current - 1}')"</c:if> >上页</a>

                    <!-- 下一页 -->
                    <c:if test="${page.current >= page.total }"></c:if>
                    <a href="javascript:;" <c:if test="${page.current < page.total }">onclick="findPage('${page.current + 1}')"</c:if> >下页</a>

                    <!-- 最后一页 -->
                    <c:if test="${page.current >= page.total }"></c:if>
                    <a href="javascript:;" <c:if test="${page.current < page.total }">onclick="findPage('${page.total}')"</c:if> >末页</a>
                </p>
            </div>
        </div><!-- 右侧搜索+游戏显示 -->
    </div><!-- 全局中心布局 -->
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
