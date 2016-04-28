<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/28
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="panel search-panel">
        <form class="form-inline search-from">
            <div class="form-group">
                <label>患者ID：</label>
                <input id="patientId" class="form-control">
            </div>
            <div class="form-group">
                <label>开始时间：</label>
                <input type="text" name="begin" class="form-control x-datepicker-day">
            </div>
            <div class="form-group">
                <label>截止时间：</label>
                <input type="text" name="end" class="form-control x-datepicker-day">
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        <button type="button" class="btn btn-warning x-btn">导出</button>
    </div>
</div>
<div class="row" id="oir">
    <div class="panel">
        <p class="explanation">基本信息</p>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">门诊号：<span id="clinicNum"></span></div>
                <div class="col-md-2">姓名：<span id="patientName"></span></div>
                <div class="col-md-2">年龄：<span id="patientAge"></span></div>
                <div class="col-md-2">公民身份：<span id="citizenship"></span></div>
                <div class="col-md-2">证件号：<span id="licenseNum"></span></div>
            </div>
            <br>

            <div class="row">
                <div class="col-md-2">民族：<span id="nation"></span></div>
                <div class="col-md-2">婚姻状况：<span id="maritalStatus"></span></div>
                <div class="col-md-2">性别：<span id="sex"></span></div>
                <div class="col-md-2">医保类型：<span id="medicareType"></span>%</div>
                <div class="col-md-2">电话：<span id="phone"></span></div>
            </div>
            <br>
        </div>
        <p class="explanation">2015年9月特殊用药统计</p>
        <table id="hospitalRecords" class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>药品名称</th>
                <th>规格</th>
                <th>处方时间</th>
                <th>单位</th>
                <th>数量</th>
                <th>用药科室</th>
                <th>用药医师</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>注射用替考拉宁(浙新昌)</td>
                <td>30mg</td>
                <td>2009-09-01 12:12:12</td>
                <td>支</td>
                <td>5</td>
                <td>妇产科</td>
                <td>张三</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/sdm/spta.js"/> "></script>
</body>
</html>
