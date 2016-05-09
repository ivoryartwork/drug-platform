<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/28
  Time: 13:44
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
    <div id="panel-0" class="panel">
        <p class="explanation"><span class="des">在院用量统计</span><a class="right x-export-btn">导出</a></p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>药品代码</th>
                <th>药品名称</th>
                <th>规格</th>
                <th>厂家</th>
                <th>总库存</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <ul id="paginationDrugStockList" class="pagination-sm"></ul>
    </div>
    <div id="panel-1" class="panel hide">
        <p class="explanation"><span class="des"></span>在院库存情况
            <a id="back" class="right x-export-btn">返回</a><a class="right x-export-btn">导出</a></p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>库存单位</th>
                <th>药品代码</th>
                <th>药品名称</th>
                <th>规格</th>
                <th>单位</th>
                <th>厂家</th>
                <th>批号</th>
                <th>总库存</th>
                <th>可供状态</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/sdm/ihus.js"/> "></script>
<script src="<c:url value="/static/js/jquery.twbsPagination.min.js"/> "></script>
</body>
</html>
