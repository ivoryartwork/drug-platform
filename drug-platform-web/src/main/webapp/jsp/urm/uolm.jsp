<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/5/20
  Time: 11:15
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
                <label>用户名：</label>
                <input id="username" class="form-control">
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
<div class="row" id="panel-0">
    <div class="panel">
        <p class="explanation">用户操作日志列表</p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>用户名</th>
                <th>操作时间</th>
                <th>操作内容</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <ul id="paginationUserOptLogList" class="pagination-sm"></ul>
    </div>
</div>
<script src="<c:url value="/static/js/urm/uolm.js"/> "></script>
</body>
</html>
