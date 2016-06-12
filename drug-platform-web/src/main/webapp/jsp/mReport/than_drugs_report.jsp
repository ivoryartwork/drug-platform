<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/6/1
  Time: 8:34
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
                <label>选择时间：</label>
                <input type="text" class="form-control x-datepicker-month" id="time">
            </div>
            <%--<div class="form-group dept">--%>
            <%--<label>科室名称：</label>--%>
            <%--<select class="form-control">--%>
            <%--</select>--%>
            <%--</div>--%>
            <button type="button" class="btn btn-primary x-btn search-btn">查询</button>
        </form>
    </div>
</div>
<div class="row">
    <div id="panel-0" class="panel hide">
        <p class="explanation"><span class="des"></span><a class="right x-export-btn">导出</a>
        </p>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>科室名称</th>
                <th>药费（元）</th>
                <th>治疗费（元）</th>
                <th>总数（元）</th>
                <th>药占比（%）</th>
                <th>目标值（%）</th>
                <th>排名</th>
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
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script src="<c:url value="/static/js/mReport/than_drugs_report.js"/>"></script>
</body>
</html>
