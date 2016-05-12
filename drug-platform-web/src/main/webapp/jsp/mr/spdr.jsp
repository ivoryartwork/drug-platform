<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/27
  Time: 13:47
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
                <label>药品名称：</label>
                        <span id="drugName">
                            <input type="text" class="form-control">
                            <ul>
                            </ul>
                        </span>
            </div>
            <div class="form-group drugSpec">
                <label>规格厂家：</label>
                <select class="form-control" id="drugSpec">
                    <option value="none">无</option>
                </select>
            </div>
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
    </div>
    <div class="panel hide" id="rankList">
        <div class="container-fluid explanation" style="font-size: 18px;line-height: 35px">
            <div class="row col-md-6">
                <span class="des">阿莫西林胶囊2015年9月各科室用量排名</span>
            </div>
            <div class="col-md-6">
                <a class="x-export-btn right"
                   style="display: inline-block;height: 33px;line-height: 33px;margin-left: 10px">导出</a>
                <select id="selectType" class="form-control right" style="width: auto;">
                    <option value="0">科室排名</option>
                    <option value="1">病区排名</option>
                    <option value="2">医生排名</option>
                    <option value="3">门诊科室排名</option>
                    <option value="4">门诊医生排名</option>
                </select>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>

            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>肾病科</td>
                <td>15226522</td>
                <td>78115</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value="/static/js/mr/spdr.js"/> "></script>
</body>
</html>
