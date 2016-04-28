<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/28
  Time: 13:40
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
                <label>截止时间：</label>
                <input type="text" name="end" class="form-control x-datepicker-day">
            </div>
        </form>
        <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
    </div>
</div>
<div class="row">
    <div id="panel-0" class="panel">
        <p class="explanation"><span class="des">在院用量统计</span><a class="right x-export-btn">导出</a></p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>库存单位</th>
                <th>药品名称</th>
                <th>规格</th>
                <th>厂家</th>
                <th>单位</th>
                <th>发放数量th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>门诊药局</td>
                <td>注射用替考拉宁(浙新昌)</td>
                <td>30mg</td>
                <td>青海制药</td>
                <td>支</td>
                <td>787878</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/sdm/cas.js"/> "></script>
</body>
</html>
