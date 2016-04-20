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
                <label>开始时间：</label>
                <input type="text" name="begin" class="form-control x-datepicker-day">
            </div>
            <div class="form-group">
                <label>截止时间：</label>
                <input type="text" name="end" class="form-control x-datepicker-day">
            </div>
            <div class="form-group">
                <label>患者ID：</label>
                <input id="patientId" class="form-control">
            </div>
            <div class="form-group">
                <label>公民身份：</label>
                <select id="citizenhood" class="form-control">
                    <option value="1">军人</option>
                    <option value="2">地方</option>
                </select>
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        <button type="button" class="btn btn-warning x-btn">导出</button>
    </div>
</div>
<div class="row">
    <div id="records" class="panel hide">
        <div class="row" style="font-weight: bold">
            <div class="col-md-2">&nbsp;&nbsp;&nbsp;姓名：<span id="name"></span></div>
            <div class="col-md-2">年龄：<span id="age"></span></div>
            <div class="col-md-2">所属单位：<span id="subordinateUnits"></span></div>
            <div class="col-md-2">挂号次数：<span id="registrationNumber"></span></div>
            <div class="col-md-2">住院次数：<span id="admission"></span></div>
            <div class="col-md-2">取药总金额：<span id="totalAmountOfDrug"></span></div>
        </div>
        <br>

        <div class="container-fluid explanation" style="font-size: 18px;line-height: 35px">
            <div class="row col-md-6">
                <span id="name1"></span>取药详情
            </div>
            <div class="col-md-6">
                <select id="selectType" class="form-control right" style="width: auto;">
                    <option value="0">按时间排序</option>
                    <option value="1">按药品汇总</option>
                </select>
            </div>
        </div>
        <div id="drugTakeRecordsByTime" class="hide">
            <table class="table table-bordered">
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
            <ul class="pagination-sm right thandrugs-pagination"></ul>
        </div>
        <div id="drugTakeRecordsByDrug" class="hide">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>药物类别</th>
                    <th>药物代码</th>
                    <th>药品名称</th>
                    <th>规格</th>
                    <th>单位</th>
                    <th>取药次数</th>
                    <th>可用天数</th>
                    <th>数量</th>
                    <th>计价金额（元）</th>
                    <th>实收金额（元）</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>6</td>
                    <td>7</td>
                    <td>8</td>
                    <td>9</td>
                    <td>10</td>
                    <td>11</td>
                </tr>
                </tbody>
            </table>
            <ul class="pagination-sm right thandrugs-pagination"></ul>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/tmpi/drugs_take_records.js"/>"></script>
</body>
</html>
