<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/11
  Time: 16:32
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
    <title>管理员管理界面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/menu.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    function toOnMouseOver(x) {
        x.color="red"
    }
    function toOnMouseOut(x) {
        x.color="black"
    }
    function toDelete(id){
        //测试用
        // alert("主键id的值为："+id)
        $.ajax({
            url:"http://localhost:8080/game?method=deleteGameBatch",
            type:"post",
            data:{ids:id},
            success:function (obj){
                if(obj){
                    alert("删除成功！")
                    location.href="http://localhost:8080/game?method=menuAdmin"
                }
                else{
                    alert("删除失败！")
                }
            },
            dataType:"json"
        })
    }
    function toBack() {
        location.href="http://localhost:8080/game?method=menuAdmin"
    }
    function toView(id){
        location.href = "http://localhost:8080/game?method=showGameDetailById&gameId="+id
    }
    function toShowAll() {
        var queryByGameName = ""
        // alert("测试搜索的游戏id："+queryById)
        $.ajax({
            //提交到后端的地址：根据游戏名称进行搜索
            url : "http://localhost:8080/game?method=searchByGameName",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                queryByGameName:queryByGameName
            },
            //请求成功之后的回调函数，固定写法
            success : function (){//在参数中接收true
                location.href="http://localhost:8080/jsp/menuAdmin.jsp"
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    //根据游戏id查询游戏
    function toQueryById(){
        var queryByGameId = $("[name='queryByGameId']").val()
        // alert("测试搜索的游戏id："+queryById)
        $.ajax({
            //提交到后端的地址：根据游戏名称进行搜索
            url : "http://localhost:8080/game?method=searchByGameId",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                queryByGameId:queryByGameId
            },
            //请求成功之后的回调函数，固定写法
            success : function (){//在参数中接收true
                location.href="http://localhost:8080/jsp/menuAdmin.jsp"
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    //根据游戏名称查询游戏
    function toQueryByName(){
        var queryByGameName = $("[name='queryByGameName']").val()
        // alert("测试搜索的游戏id："+queryById)
        $.ajax({
            //提交到后端的地址：根据游戏名称进行搜索
            url : "http://localhost:8080/game?method=searchByGameName",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                queryByGameName:queryByGameName
            },
            //请求成功之后的回调函数，固定写法
            success : function (){//在参数中接收true
                location.href="http://localhost:8080/jsp/menuAdmin.jsp"
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    //根据开发者名称查询游戏
    function toQueryByProducer(){
        var queryByProducerName = $("[name='queryByProducerName']").val()
        // alert("测试搜索的游戏id："+queryById)
        $.ajax({
            //提交到后端的地址：根据游戏名称进行搜索
            url : "http://localhost:8080/game?method=searchByProducerName",
            //提交类型
            type : "post",
            //提交到后端的参数（数据），可能有多个，用花括号表示一个集合
            data : {
                queryByProducerName:queryByProducerName
            },
            //请求成功之后的回调函数，固定写法
            success : function (){//在参数中接收true
                location.href="http://localhost:8080/jsp/menuAdmin.jsp"
            },
            //预计服务器给我返回的数据类型，json是前后端交互比较流行的格式，还有json jsonp text html，用来规范前后端交互的数据
            dataType : "json"
        })
    }
    //批量删除
    function toDeleteBatch(){
        //获取多选框被选中的个数
        //length属性：获取元素个数
        var length = $(".cks:checked").length
        // alert("选中的物品个数："+length)
        if (length > 0) {
            if (confirm("是否确定删除?")) {
                //去执行批量删除
                var ids = "";
                $(".cks:checked").each(function () {
                    //this：dom对象，是html上下文的文本对象
                    //value()是jquery语法，与this不匹配
                    //需要先将dom对象封装成jquery对象
                    ids += "," + $(this).val()
                })
                //把字符串最前面那个.切割掉
                ids = ids.substring(1)

                // alert("需要删除的条目："+ids)
                //用ajax提交数据
                $.ajax({
                    url: "http://localhost:8080/game?method=deleteGameBatch",
                    type: "post",
                    data: {ids: ids},
                    success: function (obj) {
                        if (obj) {
                            alert("批量删除游戏成功！")
                            location.href = "http://localhost:8080/game?method=menuAdmin"
                            //这里应该跳转到当前用户id所在的购物车界面
                        }
                        else {
                            alert("批量删除游戏失败！")
                        }
                    },
                    dataType: "json"
                })
            }
        }
        else {
            alert("请至少选择一款待删除的游戏！")
        }
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
        $("#chkOneBtn").click(function (){
            $(".cks").each(function (){
                this.checked = ! this.checked;
            })
        })
    })
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<!-- 全局中心布局 -->
<div id="global_main">
    <!-- 管理用户 -->
    <div id="admin_user">
        <!-- 用户管理标题 -->
        <div id="user_header_main">
            <h1 class="h1">用户管理</h1>
        </div><!-- 用户管理标题 -->
        <!-- 用户管理链接 -->
        <div id="admin_user_action">
            <a href="http://localhost:8080/game?method=manager_user">查看用户列表</a><br><br>
            <a href="http://localhost:8080/game?method=ShowChangeList">处理修改申请表</a><br><br>
            <a href="http://localhost:8080/game?method=ShowPutList">处理上架申请表</a><br><br>
            <a href="http://localhost:8080/game?method=ShowTakeList">处理下架申请表</a><br><br>
            <a href="http://localhost:8080/game?method=exit">管理员退出登录</a><br><br>
            <hr>
            <label>管理员您好，<br><br>您可以通过此界面进行相关管理</label><br><br><br>
            <img src="../img/manager.png" alt="管理员">
        </div>
    </div><!-- 管理用户 -->
    <!-- 管理游戏 -->
    <div id="admin_game">
        <!-- 游戏管理标题 -->
        <div id="game_header_main">
            <h1 class="h1">游戏管理</h1>
        </div><!-- 游戏管理标题 -->
        <!-- 管理员的搜索功能 -->
        <div id="game_query">
            <!-- 根据游戏ID搜索 -->
            <div id="game_queryById">
                <input type="text" name="queryByGameId" id="queryById" placeholder="输入游戏ID...">
                <input type="button" class="special" value="搜索" onclick="toQueryById()">
            </div><!-- 根据游戏ID搜索 -->
            <!-- 根据游戏名搜索 -->
            <div id="game_queryByName">
                <input type="text" name="queryByGameName" id="queryByName" placeholder="输入游戏名称...">
                <input type="button" class="special" value="搜索" onclick="toQueryByName()">
            </div><!-- 根据游戏名搜索 -->
            <!-- 根据游戏开发者搜索 -->
            <div id="game_queryByProducer">
                <input type="text" name="queryByProducerName" id="queryByProducer" placeholder="输入开发者名称...">
                <input type="button" class="special" value="搜索" onclick="toQueryByProducer()">
            </div><!-- 根据游戏开发者搜索 -->
        </div><!-- 管理员的搜索功能 -->
        <!-- 显示游戏列表 -->
        <div id="game_showList">
            <table class="gradient-style">
                <tr>
                    <td><input type="button" class="cancel" value="批量删除" onclick="toDeleteBatch()"></td>
                    <td><input type="button" class="cancel" value="全选" onclick="toCheckAll()"></td>
                    <td><input type="button" class="cancel" value="全不选" onclick="toCheckNone()"></td>
                    <td><input type="button" class="cancel" value="反选" onclick="toInverse()"></td>
                    <td><input type="button" class="cancel" value="显示所有" onclick="toShowAll()"></td>
                </tr>
                <tr>
                    <th>
                        选择<input type="checkbox" id="chkOneBtn">
                    </th>
                    <th>id</th>
                    <th>名称</th>
                    <th>开发者</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${games}" var="game">
                    <tr>

                    </tr>
                    <tr align="center">
                        <td><input type="checkbox" class="cks" value="${game.id}"></td>
                        <td>${game.id}</td>
                        <td>${game.name}</td>
                        <td>${game.p_name}</td>
                        <td>
                            <input type="button" class="button" value="删除" onclick="toDelete(${game.id})">
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div><!-- 右侧显示游戏列表 -->
        <div class="page_style">
            <label>共&nbsp;&nbsp;<i>${page.rows}</i>&nbsp;&nbsp;条记录</label>
            <!-- 第一页-->
            <p>  <c:if test="${page.current <= 1 }"></c:if>
                <a <c:if test="${page.current > 1 }">href="http://localhost:8080/game?method=menuAdmin&current=1" </c:if> >首页</a>
                <!-- 上一页 -->
                <c:if test="${page.current <= 1 }"></c:if>
                <a <c:if test="${page.current > 1 }">href="http://localhost:8080/game?method=menuAdmin&current=${page.current - 1}" </c:if> >上页</a>
                <!-- 下一页 -->
                <c:if test="${page.current >= page.total }"></c:if>
                <a <c:if test="${page.current < page.total }">href="http://localhost:8080/game?method=menuAdmin&current=${page.current + 1}" </c:if> >下页</a>
                <!-- 最后一页 -->
                <c:if test="${page.current >= page.total }"></c:if>
                <a <c:if test="${page.current < page.total }">href="http://localhost:8080/game?method=menuAdmin&current=${page.total}" </c:if> >末页</a>
            </p>
        </div>
    </div><!-- 管理游戏 -->

</div><!-- 全局中心布局 -->
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
