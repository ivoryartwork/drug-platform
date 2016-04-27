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
    <div class="panel">
        <form class="form-inline search-from">
            <div class="form-group">
                <label>开始时间：</label>
                <input type="text" class="form-control x-datepicker-month">
            </div>
            <div class="form-group">
                <label>结束时间：</label>
                <input type="text" class="form-control x-datepicker-month">
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn">查询</button>
    </div>
    <div class="panel">
        <div class="container-fluid explanation" style="font-size: 18px;line-height: 35px">
            <div class="row col-md-6">
                <span id="des">2015年9月1日至2015年9月3日单张处方排名</span>
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
                <td>Z2189998</td>
                <td>风湿科门诊</td>
                <td>刘虎</td>
                <td>100265623</td>
                <td>张继</td>
                <td>787878</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/mr/par.js"/> "></script>
</body>
</html>
