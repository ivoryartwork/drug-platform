<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/27
  Time: 13:48
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
                <label>开始时间：</label>
                <input type="text" name="begin" class="form-control x-datepicker-day">
            </div>
            <div class="form-group">
                <label>结束时间：</label>
                <input type="text" name="end" class="form-control x-datepicker-day">
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        <button id="back" type="button" class="btn btn-info x-btn">返回</button>
    </div>
    <div class="panel" id="panel-0">
        <div class="container-fluid explanation" style="font-size: 18px;line-height: 35px">
            <div class="row col-md-6">
                <span class="des">2015年9月1日至2015年9月3日单张处方排名</span>
            </div>
            <div class="col-md-6">
                <a class="x-export-btn right"
                   style="display: inline-block;height: 33px;line-height: 33px;margin-left: 10px">导出</a>
                <select id="selectType" class="form-control right" style="width: auto;">
                    <option value="0">单张处方排名</option>
                    <option value="1">医生处方排名</option>
                </select>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            </thead>
            <tbody>
            </tbody>
        </table>
        <ul id="paginationParList" class="pagination-sm"></ul>
    </div>
    <div class="panel hide" id="panel-1">
        <p class="explanation"><span class="des">处方号1232明细</span></p>

        <div class="container-fluid">
            <br>

            <div class="row">
                <div class="col-md-3">开处方科室：<span id="deptName"></span></div>
                <div class="col-md-3">医生：<span id="doctor"></span></div>
                <div class="col-md-3">门诊ID：<span id="visitNo"></span></div>
                <div class="col-md-3">患者姓名：<span id="patientName"></span></div>
            </div>
            <br>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>处方号</th>
                <th>时间</th>
                <th>就诊号</th>
                <th>药品代码</th>
                <th>药品名称</th>
                <th>规格</th>
                <th>单位</th>
                <th>数量</th>
                <th>金额</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/mr/par.js"/> "></script>
</body>
</html>
