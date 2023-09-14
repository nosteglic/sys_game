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
    <title>填写游戏上架申请表</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/game_table.css"><!--游戏表样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toSubmit(){
        var producerId = $("[name='producerId']").val()
        var gameName = $("[name='gameName']").val()
        var gameType = $("select[name='gameType']").val()
        var gameAge = $("select[name='gameAge']").val()
        var gamePrice = $("[name='gamePrice']").val()
        var gameDiscount = $("[name='gameDiscount']").val()
        var gameIntroduction = $("[name='gameIntroduction']").val()
        //测试用
        // alert(producerId+gameName+","+gameType+","+gameAge+","+gamePrice+","+gameDiscount+","+gameIntroduction)

        $.ajax({
            url:"http://localhost:8080/user?method=toPutGame",
            type:"post",
            data:{
                producerId:producerId,
                gameName:gameName,
                gameType:gameType,
                gameAge:gameAge,
                gamePrice:gamePrice,
                gameDiscount:gameDiscount,
                gameIntroduction:gameIntroduction
            },
            success:function (obj){
                if(obj){
                    alert("提交上架申请表成功！")
                    location.href="http://localhost:8080/user?method=showGameList"
                }
                else{
                    alert("提交上架申请表失败！")
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
        <h1 class="h1">开发者上架游戏申请表</h1>
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
                    <td class>申请者名称：</td>
                    <td>
                        <input type="text" name="producerName" readonly  unselectable="on" value="${loginUser.username}">
                    </td>
                </tr>
                <tr>
                    <td class>游戏名：</td>
                    <td>
                        <input type="text" name="gameName">
                    </td>
                </tr>
                <tr class>
                    <td class>游戏类型：</td>
                    <td>
                        <select name="gameType">
                            <option value="休闲">休闲</option>
                            <option value="体育">体育</option>
                            <option value="冒险">冒险</option>
                            <option value="动作">动作</option>
                            <option value="联机">联机</option>
                            <option value="模拟">模拟</option>
                            <option value="独立">独立</option>
                            <option value="竞速">竞速</option>
                            <option value="策略">策略</option>
                            <option value="角色扮演">角色扮演</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>游戏分级：</td>
                    <td>
                        <select name="gameAge">
                            <option value="10">10+</option>
                            <option value="13">13+</option>
                            <option value="15">15+</option>
                            <option value="18">18+</option>
                            <option value="19">19+</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>游戏价格：</td>
                    <td>
                        <input type="text" name="gamePrice">
                    </td>
                </tr>
                <tr>
                    <td>游戏折扣：</td>
                    <td>
                        <input type="text" name="gameDiscount">&nbsp;&nbsp;%
                    </td>
                </tr>
            </table>
        </div>
        <div id="content_main_info">
            <table class="gradient-style">
                <tr>
                    <td>游戏介绍：</td>
                    <td>
                        <textarea name="gameIntroduction" rows="6" cols="50"></textarea>
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
