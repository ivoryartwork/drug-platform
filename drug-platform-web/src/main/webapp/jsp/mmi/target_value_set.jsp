<%--
  Created by IntelliJ IDEA.
  User: Yaochao
  Date: 2016/4/19
  Time: 10:39
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
            <select class="form-control">
                <option>药费比</option>
                <option>门诊次均药费</option>
                <option>住院次均药费</option>
                <option>抗菌药物使用强度</option>
                <option>住院抗菌药物使用率</option>
                <option>门诊抗菌药物处方比例</option>
                <option>急诊抗菌药物处方比例</option>
                <option>I类切口预防使用抗菌药物比例</option>
            </select>
            <button class="btn btn-warning x-btn">导出</button>
            <div class="form-group right">
                <input type="text" class="form-control" placeholder="科室名称">
                <button class="btn btn-primary">搜索</button>
            </div>
        </form>
    </div>
    <div class="panel">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>科室名称</th>
                <th>药费比目标值（%）</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>全院</td>
                <td>53</td>
                <td>
                    <div class="btn-group">
                        <button type="button" class="btn btn-danger btn-sm dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            操作 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a onclick="">查看修改记录</a></li>
                            <li><a onclick="">修改目标值</a></li>
                        </ul>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <%--<ul class="pagination-sm right thandrugs-pagination"></ul>--%>
    </div>
</div>
<script src="<c:url value="/static/js/mmi/target_value_set.js"/>"></script>
</body>
</html>
