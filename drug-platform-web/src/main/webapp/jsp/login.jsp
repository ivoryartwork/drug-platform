<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/31
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>解放军总医院药品监管平台</title>
    <link rel="stylesheet" href="<c:url value="/static/css/login_page.css"/>">
</head>
<body>
<div class="container">
    <div class="header">
        <%--<a class="header_logo"></a>--%>
        <span class="header_title">解放军总医院药品监管平台</span>

        <div class="header_link"><a href="http://www.301hospital.com.cn/" target="_blank">了解我们</a></div>
    </div>
    <div class="content">
        <div class="content_wrapper">
            <div class="login_container" id="login">
                <div class="login_tab">
                    <div class="switch" id="switch">
                        <a class="switch_btn_focus" hidefocus="true" href="javascript:void(0);"
                           data-toggle="login_p">登录</a>
                    </div>
                </div>
                <div class="login_tab_content">
                    <div id="login_p" class="tab">
                        <form class="login" onsubmit="return false">
                            <label class="error"></label><label>&nbsp;</label>
                            <input type="text" name="username" placeholder="用户名">
                            <input type="password" name="password" placeholder="密码">
                            <input type="checkbox"><label class="lable-remember-password">记住密码</label>
                            <a onclick="login()">登录</a>
                        </form>
                    </div>
                </div>
            </div>
            <div class="login_pictures">
            </div>
        </div>
    </div>
    <div class="footer"><span class="gray">Copyright © 1999-2016 PLA General Hospital. All Rights Reserved. </span>
    </div>
</div>
<script src="<c:url value="/static/js/jquery-1.11.2.min.js"/>"></script>
<script>
    function login() {
        if ($(".login input[name='username']").val() == '') {
            error("请输入用户名")
            return;
        }
        if ($(".login input[name='password']").val() == '') {
            error("请输入密码")
            return;
        }
        var data = $(".login input").serialize();
        $.ajax({
            url: "login/check",
            type: "POST",
            timeout: 5000,
            dataType: "text",
            data: data,
            success: function (data) {
                data = Number(data);
                if (data == 0) {
                    window.location.href = ""
                } else if (data == 1) {
                    //用户名不存在
                    error("用户名不存在")
                } else if (data == 2) {
                    //密码错误
                    error("密码错误")
                }
            }
        })
    }

    function error(msg) {
        $(".error").html(msg);
    }

</script>
</body>
</html>
