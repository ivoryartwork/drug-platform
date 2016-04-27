<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/19
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
            <button type="button" class="btn btn-warning x-btn">导出</button>
        </form>
    </div>
</div>
<div class="row hide" id="oir">
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
        <p class="explanation">住院记录</p>
        <table id="hospitalRecords" class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>科室</th>
                <th>医生</th>
                <th>入院日期</th>
                <th>出院日期</th>
                <th>病房</th>
                <th>护士</th>
                <th>入院病因</th>
                <th>体检结果</th>
                <th>医院建议</th>
                <th>医生处理意见</th>
                <th>用药记录</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/tmpi/outpatient_inpatient_records.js"/>"></script>
</body>
</html>
