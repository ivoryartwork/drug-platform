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
                <input type="text" class="form-control x-datepicker-month">
            </div>
            <div class="form-group dept">
                <label>科室名称：</label>
                <select class="form-control">
                </select>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value="/static/js/mReport/than_drugs_report.js"/>"></script>
</body>
</html>
