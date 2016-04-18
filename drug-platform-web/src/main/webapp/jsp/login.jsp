<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/18
  Time: 12:40
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/login.css"/>"/>
</head>
<body>
<body>
<div class="wrapper">
    <div class="content">
        <div id="form_wrapper" class="form_wrapper">
            <form class="register">
                <h3>Register</h3>

                <div class="column">
                    <div>
                        <label>First Name:</label>
                        <input type="text"/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>Last Name:</label>
                        <input type="text"/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>Website:</label>
                        <input type="text" value="http://"/>
                        <span class="error">This is an error</span>
                    </div>
                </div>
                <div class="column">
                    <div>
                        <label>Username:</label>
                        <input type="text"/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>Email:</label>
                        <input type="text"/>
                        <span class="error">This is an error</span>
                    </div>
                    <div>
                        <label>Password:</label>
                        <input type="password"/>
                        <span class="error">This is an error</span>
                    </div>
                </div>
                <div class="bottom">
                    <div class="remember">
                        <input type="checkbox"/>
                        <span>Send me updates</span>
                    </div>
                    <input type="submit" value="Register"/>
                    <a href="index.html" rel="login" class="linkform">You have an account already? Log in here</a>

                    <div class="clear"></div>
                </div>
            </form>
            <form class="login active" onsubmit="return false">
                <h3>登录</h3>

                <div>
                    <label>用户名:</label>
                    <input type="text" name="username"/>
                    <span class="error">This is an error</span>
                </div>
                <div>
                    <label>密码:</label>
                    <input type="password" name="password"/>
                    <span class="error">This is an error</span>
                </div>
                <div class="bottom">
                    <div class="remember"><input type="checkbox"/><span>记住密码</span></div>
                    <input type="submit" value="登录" onclick="login()"/>
                    <a href="#" rel="register">没有账户？注册</a>

                    <div class="clear"></div>
                </div>
            </form>
            <form class="forgot_password">
                <h3>Forgot Password</h3>

                <div>
                    <label>Username or Email:</label>
                    <input type="text"/>
                    <span class="error">This is an error</span>
                </div>
                <div class="bottom">
                    <input type="submit" value="Send reminder"/>
                    <a href="index.html" rel="login" class="linkform">Suddenly remebered? Log in here</a>
                    <a href="register.html" rel="register" class="linkform">You don't have an account? Register here</a>

                    <div class="clear"></div>
                </div>
            </form>
        </div>
        <div class="clear"></div>
    </div>
</div>


<!-- The JavaScript -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-1.11.2.min.js"/> "></script>
<script type="text/javascript">
    $(function () {
        //the form wrapper (includes all forms)
        var $form_wrapper = $('#form_wrapper'),
        //the current form is the one with class active
                $currentForm = $form_wrapper.children('form.active'),
        //the change form links
                $linkform = $form_wrapper.find('.linkform');

        //get width and height of each form and store them for later
        $form_wrapper.children('form').each(function (i) {
            var $theForm = $(this);
            //solve the inline display none problem when using fadeIn fadeOut
            if (!$theForm.hasClass('active'))
                $theForm.hide();
            $theForm.data({
                width: $theForm.width(),
                height: $theForm.height()
            });
        });

        //set width and height of wrapper (same of current form)
        setWrapperWidth();

        /*
         clicking a link (change form event) in the form
         makes the current form hide.
         The wrapper animates its width and height to the
         width and height of the new current form.
         After the animation, the new form is shown
         */
        $linkform.bind('click', function (e) {
            var $link = $(this);
            var target = $link.attr('rel');
            $currentForm.fadeOut(400, function () {
                //remove class active from current form
                $currentForm.removeClass('active');
                //new current form
                $currentForm = $form_wrapper.children('form.' + target);
                //animate the wrapper
                $form_wrapper.stop()
                        .animate({
                            width: $currentForm.data('width') + 'px',
                            height: $currentForm.data('height') + 'px'
                        }, 500, function () {
                            //new form gets class active
                            $currentForm.addClass('active');
                            //show the new form
                            $currentForm.fadeIn(400);
                        });
            });
            e.preventDefault();
        });

        function setWrapperWidth() {
            $form_wrapper.css({
                width: $currentForm.data('width') + 'px',
                height: $currentForm.data('height') + 'px'
            });
        }

        /*
         for the demo we disabled the submit buttons
         if you submit the form, you need to check the
         which form was submited, and give the class active
         to the form you want to show
         */
        $form_wrapper.find('input[type="submit"]')
                .click(function (e) {
                    e.preventDefault();
                });
    });

    function login() {
        var data = $(".login input").serialize();
        $.ajax({
            url: "login/check",
            type: "POST",
            timeout: 5000,
            dataType: "text",
            data: data,
            success: function (data) {
                if (data == "success") {
                    window.location.href = ""
                }
            }
        })
    }
</script>
</body>
</body>
</html>

