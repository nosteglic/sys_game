<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/10
  Time: 12:50
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
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<head>
    <title>我的购物车</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/register.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    $(function (){
        //全选/全不选/反选，使用一个按钮完成
        $("#chkBtn").click(function (){
            $(".cks").each(function (){
                this.checked = ! this.checked;
            })
        })
    })

    function toAdd(){
        location.href="http://localhost:8080/user?method=showGameList"//跳转到主界面
    }
    function toDelete() {
        var userId = $("[name='userId']").val()
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
                    url: "http://localhost:8080/user?method=deleteShopping",
                    type: "post",
                    data: {ids: ids},
                    success: function (obj) {
                        if (obj) {
                            alert("删除购物车物品成功！")
                            location.href = "http://localhost:8080/user?method=shoppingCart&userId="+userId
                            //这里应该跳转到当前用户id所在的购物车界面
                        }
                        else {
                            alert("删除购物车物品失败！")
                        }
                    },
                    dataType: "json"
                })
            }
        }
        else {
            alert("请至少选择一样待删除的物品！")
        }
    }
</script>
<body>
<input type="hidden" name="userId" value="${loginUser.id}">
<!--引入头部div-->
<%@include file="header.jsp" %>
    <div id="global_main">
        <!-- 购物车题头 -->
        <div id="shoppingCart_header">
            <h1 class="h1">我的购物车</h1>
        </div>
        <!-- 购物车界面的操作 -->
        <div id="shoppingCart_action">
            <table>
                <tr>
                    <td><input type="button" class="special" value="继续添加商品" onclick="toAdd()"></td>
                    <td><input type="button" class="special" value="删除" onclick="toDelete()"></td>
                </tr>
            </table>
        </div>
        <!-- 购物车界面的物品显示 -->
        <div id="shoppingCart_things">
            <!-- table形式呈现 -->
            <table  class="gradient-style">
                <tr>
                    <th>选择<input type="checkbox" id="chkBtn"></th>
                    <!-- th：加粗大字 -->
                    <th>游戏ID</th>
                    <th>游戏名称</th>
                    <th>游戏价格</th>
                </tr>
                <!-- 遍历域对象中的集合 -->
                <!-- items：el表达式    var：每个字段-->
                <c:forEach items="${shoppings}" var="shopping">
                    <tr align="center">
                        <td><input type="checkbox" class="cks" value="${shopping.id}"></td>
                        <!-- name与数据库无关，与实体类Shopping的对象需要一一匹配 -->
                        <td>${shopping.g_id}</td>
                        <td>${shopping.g_name}</td>
                        <td>${shopping.g_price}</td>
                    </tr>
                </c:forEach>
            </table>
        </div><!-- 购物车界面的物品显示 -->
    </div>
<!--引入底部div-->
<%@include file="footer.jsp" %>
</body>
</html>
