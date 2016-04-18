<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/18
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
    <link href="<c:url value="/static/js/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/dashboard.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-1.11.2.min.js"/>"></script>
    <link href="<c:url value="/static/js/bootstrap/datepicker/css/bootstrap-datepicker3.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/bootstrap/datepicker/js/bootstrap-datepicker.min.js"/>"></script>
    <script src="<c:url value="/static/js/bootstrap/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"/>"></script>
    <script src="<c:url value="/static/js/highcharts/highcharts.js"/>"></script>
    <script src="<c:url value="/static/js/jquery.twbsPagination.min.js"/>"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">解放军总医院药品监管平台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">当前用户：刘玉（管理员）</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a>用药指标监控</a></li>
                <li><a href="than_drugs.html">药费比</a></li>
                <li><a href="#">药品用量</a></li>
                <li><a href="#">门诊次均药费</a></li>
                <li><a href="#">住院次均药费</a></li>
                <li><a href="#">抗菌药物使用强度</a></li>
                <li><a href="#">住院抗菌药物使用率</a></li>
                <li><a href="#">门诊抗菌药物处方比例</a></li>
                <li><a href="#">急诊抗菌药物处方比例</a></li>
                <li><a href="#">I类切口预防使用抗菌药物比例</a></li>
                <li><a href="#">目标值设置</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>重点药品监控</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>取药及处方查询</a></li>
                <li><a href="">取药记录查询</a></li>
                <li><a href="">门诊病人住院记录</a></li>
                <li><a href="">单品种处方查询</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>用药排名</a></li>
                <li><a href="">单品种药品用量排名</a></li>
                <li><a href="">处方金额排名</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>月报表</a></li>
                <li><a href="">科室药费比</a></li>
                <li><a href="">科室门诊次均药费</a></li>
                <li><a href="">病区住院次均药费</a></li>
                <li><a href="">病区抗菌药物使用强度</a></li>
                <li><a href="">病区住院抗菌药物使用率</a></li>
                <li><a href="">科室门诊抗菌药物处方比例</a></li>
                <li><a href="">急诊抗菌药物处方比例</a></li>
                <li><a href="">I类切口预防使用抗菌药物比例</a></li>
                <li><a href="">不合理用药记录</a></li>
                <li><a href="">药品用量统计</a></li>
                <li><a href="">联合用药统计</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>特殊管理药品监控</a></li>
                <li><a href="">在院用量统计</a></li>
                <li><a href="">临床应用统计</a></li>
                <li><a href="">单病人用药统计</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li class="active"><a>用户权限管理</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <tiles:insertAttribute name="main"/>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>
<script>
    Main.init();
</script>
</body>
</html>
