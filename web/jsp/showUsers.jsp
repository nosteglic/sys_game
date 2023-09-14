<%--
  Created by IntelliJ IDEA.
  User: nosteglic
  Date: 2021/1/13
  Time: 15:08
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
    <title>用户管理界面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="../css/default.css"><!--默认样式-->
<link rel="stylesheet" href="../css/style.css"><!--表格按钮样式-->
<link rel="stylesheet" href="../css/show_table.css"><!--登录div样式-->
<%--引入jquery控件--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>
    function toDeleteBatch() {
        var length = $(".cks:checked").length
        if (length > 0) {
            if (confirm("是否确定批量注销用户?")) {
                var ids = "";
                $(".cks:checked").each(function () {
                    ids += "," + $(this).val()
                })
                ids = ids.substring(1)

                //用ajax提交数据
                $.ajax({
                    url: "http://localhost:8080/game?method=deleteUserBatch",
                    type: "post",
                    data: {ids: ids},
                    success: function (obj) {
                        if (obj) {
                            alert("批量注销用户成功！")
                            location.href = "http://localhost:8080/game?method=manager_user"
                        } else {
                            alert("批量注销用户失败！")
                        }
                    },
                    dataType: "json"
                })
            }
        }
        else {
            alert("请至少选择一条数据")
        }
    }

    function toDelete(id){
        $.ajax({
            url:"http://localhost:8080/game?method=deleteUserById",
            type:"post",
            data:{id:id},
            success:function (obj){
                if(obj){
                    alert("注销用户成功！")
                    location.href="http://localhost:8080/game?method=manager_user"
                }
                else{
                    alert("注销用户失败！")
                }
            },
            dataType:"json"
        })
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
        $(".chkOneBtn").click(function (){
            $(".cks").each(function (){
                this.checked = ! this.checked;
            })
        })
    })
</script>
<body>
<!--引入头部div-->
<%@include file="header.jsp" %>
<div class="global_main">
    <!-- 主内容题头 -->
    <div class="header_main">
        <h1 class="h1">用户管理</h1>
    </div>
    <!-- 主内容功能区 -->
    <div class="action_main">
        <table>
            <tr>
                <td><input type="button" class="special" value="全选" onclick="toCheckAll()"></td>
                <td><input type="button" class="special" value="全不选" onclick="toCheckNone()"></td>
                <td><input type="button" class="special" value="反选" onclick="toInverse()"></td>
                <td><input type="button" class="cancel" value="批量注销用户" onclick="toDeleteBatch()"></td>
                <td><a href="http://localhost:8080/game?method=menuAdmin">返回</a></td>
            </tr>
        </table>
    </div>
    <!-- 主内容核心 -->
    <div class="content_main">
        <!-- table -->
        <table class="gradient-style">
            <tr>
                <th>选择<input type="checkbox" class="chkOneBtn"></th>
                <th>用户id</th>
                <th>用户名</th>
                <th>用户身份</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr align="center">
                    <td><input type="checkbox" class="cks" value="${user.id}"></td>
                    <!-- name与数据库无关，与实体类Student的对象需要一一匹配 -->
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>
                            <c:choose>
                                <c:when test="${user.type == 1}">普通用户</c:when>
                                <c:when test="${user.type == 2}">个人开发者</c:when>
                                <c:when test="${user.type == 3}">公司开发者</c:when>
                            </c:choose>
                    </td>
                    <td>
                        <input type="button" value="注销" onclick="toDelete(${user.id})">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
