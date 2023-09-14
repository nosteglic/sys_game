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
    <title>填写下架游戏申请表</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/game_table.css"><!--游戏表样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toSubmit(){
        var producerId = $("[name='producerId']").val()
        var gameId = $("[name='gameId']").val()
        var gameName = $("[name='gameName']").val()
        var offlineReason = $("[name='offlineReason']").val()
        //测试用
        // alert(producerId+","+gameId+","+gameName+","+offlineReason)

        $.ajax({
            url:"http://localhost:8080/user?method=toTakeGame",
            type:"post",
            data:{
                producerId:producerId,
                gameId:gameId,
                gameName:gameName,
                offlineReason:offlineReason
            },
            success:function (obj){
                if(obj){
                    alert("提交下架申请表成功！")
                    location.href="http://localhost:8080/user?method=selectOnlineGame&userId="+producerId
                }
                else{
                    alert("提交下架申请表失败！")
                }
            },
            dataType:"json"
        })
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
        <h1 class="h1">开发者下架游戏申请表</h1>
    </div>
    <!-- 主内容核心 -->
    <div id="content_main">
        <!-- table -->
        <div id="content_main_table">
            <table class="gradient-style">
                <tr>
                    <td>申请者ID：</td>
                    <td>
                        <input type="text" name="producerId" readonly  unselectable="on" value="${loginUser.id}">
                    </td>
                </tr>
                <tr>
                    <td>申请者名称：</td>
                    <td>
                        <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.username}">
                    </td>
                </tr>
                <tr>
                    <td>申请下架的游戏ID：</td>
                    <td>
                        <input type="text" name="gameId" readonly  unselectable="on" value="${chsOnlineGame.id}">
                    </td>
                </tr>
                <tr>
                    <td>申请下架的游戏名：</td>
                    <td>
                        <input type="text" name="gameName" readonly  unselectable="on" value="${chsOnlineGame.name}">
                    </td>
                </tr>

            </table>
        </div>
        <div id="content_main_info">
            <table class="gradient-style">
                <tr>
                    <td>请填写下架原因：</td>
                    <td>
                        <textarea name="offlineReason" rows="6" cols="50"></textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!-- 主内容功能区 -->
    <div id="action_main">
        <input type="button" id="space1" class="cancel" value="提交" onclick="toSubmit()">
        <input type="button" id="space2" class="cancel" value="返回" onclick="toBack()">
    </div>
</div>
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
