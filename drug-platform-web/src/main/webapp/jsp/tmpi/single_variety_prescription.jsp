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
    <div class="panel">
        <form class="form-inline search-from">
            <div class="form-group">
                <label>药品名称：</label>
                <input type="text" class="form-control">
            </div>
            <div class="form-group">
                <label>规格厂家：</label>
                <input type="text" class="form-control">
            </div>
            <div class="form-group">
                <label>开始时间：</label>
                <input type="text" class="form-control x-datepicker-month">
            </div>
            <div class="form-group">
                <label>结束时间：</label>
                <input type="text" class="form-control x-datepicker-month">
            </div>
        </form>
        <form class="form-inline search-from">
            <div class="form-group type">
                <label>用药区域：</label>
                <select class="form-control">
                    <option value="1">全院</option>
                    <option value="2">门诊</option>
                    <option value="3">住院</option>
                </select>
            </div>
            <div class="form-group dept">
                <label>科室名称：</label>
                <select class="form-control">
                    <option value="none">不分科室</option>
                    <option value="112">骨科</option>
                    <option value="12">妇产科</option>
                    <option value="23213">外科</option>
                    <option value="23432">内科</option>
                </select>
            </div>
            <div class="form-group">
                <label>医生姓名：</label>
                <select class="form-control">
                    <option value="none">不分医生</option>
                    <option value="21">医生1</option>
                    <option value="2">医生2</option>
                    <option value="3w3e">医生3</option>
                    <option value="se">医生4</option>
                </select>
            </div>
            <div class="form-group costType">
                <label>类别：</label>
                <select class="form-control">
                    <option value="none">不分类别</option>
                    <option value="sdf">军人</option>
                    <option value="sdfsd">地方医保</option>
                    <option value="sdfsdf">全费</option>
                </select>
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn">查询</button>
        <button id="back" type="button" class="btn btn-info x-btn">返回</button>
    </div>
    <div id="panel-0" class="panel">
        <div class="row">
            <div class="col-md-12">
                <p class="explanation">2015年9月1日至2015年9月3日注射用替考拉宁相关处方列表<a class="right x-export-btn">导出</a></p>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>处方号</th>
                        <th>开处方诊室</th>
                        <th>医生姓名</th>
                        <th>门诊ID</th>
                        <th>患者姓名</th>
                        <th>处方金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td><a onclick="viewPrescription('Z2189998')">Z2189998</a></td>
                        <td>风湿科门诊</td>
                        <td>刘虎</td>
                        <td>100265623</td>
                        <td>张继</td>
                        <td>8986</td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
    <div id="panel-1" class="panel hide">
        <div class="row">
            <div class="col-md-12">
                <p class="explanation">处方号<span id="prescriptionId"></span>明细<a class="right x-export-btn">导出</a></p>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>时间</th>
                        <th>就诊号</th>
                        <th>药品代码</th>
                        <th>药品名称</th>
                        <th>规格厂家</th>
                        <th>数量</th>
                        <th>金额（元）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>2015-06-02 12:12:12</td>
                        <td>501</td>
                        <td>303565313</td>
                        <td>注射用替考拉宁</td>
                        <td>0.5g青海制药</td>
                        <td>1盒</td>
                        <td>125</td>
                    </tr>
                    </tbody>
                </table>
                <ul class="pagination-sm right thandrugs-pagination"></ul>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/tmpi/single_variety_prescription.js"/>"></script>
</body>
</html>
