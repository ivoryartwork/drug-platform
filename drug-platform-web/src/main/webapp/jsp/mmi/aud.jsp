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
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab"
                                                  data-toggle="tab">单品种查询</a></li>
        <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab"
                                   data-toggle="tab">用药单位查询</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="tab1">
            <div class="panel">
                <form class="form-inline search-from">
                    <div class="form-group">
                        <label>药品名称：</label>
                        <input type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>规格厂家：</label>
                        <select class="form-control">
                            <option>0.25g哈药总厂</option>
                            <option>0.5g华北制药</option>
                            <option>1g晋博康</option>
                        </select>
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
                    <div class="form-group">
                        <label>科室名称：</label>
                        <select class="form-control">
                            <option>不分科室</option>
                            <option>骨科</option>
                            <option>妇产科</option>
                            <option>外科</option>
                            <option>内科</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>病区名称：</label>
                        <select class="form-control">
                            <option>不分病区</option>
                            <option>一病区</option>
                            <option>二病区</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>类别：</label>
                        <select class="form-control">
                            <option>不分类别</option>
                            <option>军人</option>
                            <option>地方医保</option>
                            <option>全费</option>
                        </select>
                    </div>
                </form>
                <button type="button" class="btn btn-primary x-btn">查询</button>
                <button type="button" class="btn btn-warning x-btn">导出</button>
                <button id="back-0" type="button" class="btn btn-info x-btn">返回</button>
            </div>
            <div id="panel-0" class="panel">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">全院2015年9月各药品抗菌药物使用强度列表</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>药品名称</th>
                                <th>DDD</th>
                                <th>日剂量</th>
                                <th>DDDs</th>
                                <th>排名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a onclick="singleSwitchToAll()">甲硝唑注射液</a></td>
                                <td>6g</td>
                                <td>0.5-4g</td>
                                <td>68</td>
                                <td>1</td>
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
                        <p class="explanation">甲硝唑注射液全院使用强度趋势</p>

                        <div id="singleAudColumn" class="x-column"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation"><span class="title-name"></span>2015年9月各科室甲硝唑注射液使用强度</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>科室名称</th>
                                <th>使用强度</th>
                                <th>目标值</th>
                                <th>排名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a onclick="singleSwitchToDepartment()">肾病科</a></td>
                                <td>282956</td>
                                <td>363535</td>
                                <td>4455411</td>
                            </tr>
                            </tbody>
                        </table>
                        <ul class="pagination-sm right thandrugs-pagination"></ul>
                    </div>
                </div>
            </div>
            <div id="panel-2" class="panel hide">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">甲硝唑注射液肾病科使用强度趋势</p>

                        <div id="departmentSingleAudColumn" class="x-column"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation"><span class="title-name"></span>甲硝唑注射液2015年9月肾病科各病区使用强度</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>病区名称</th>
                                <th>使用强度</th>
                                <th>目标值</th>
                                <th>排名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a onclick="singleSwitchToWard()">肾病科一区</a></td>
                                <td>282956</td>
                                <td>363535</td>
                                <td>4455411</td>
                            </tr>
                            </tbody>
                        </table>
                        <ul class="pagination-sm right thandrugs-pagination"></ul>
                    </div>
                </div>
            </div>
            <div id="panel-3" class="panel hide">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">甲硝唑注射液肾病科一区使用强度趋势</p>

                        <div id="wardSingleAudColumn" class="x-column"></div>
                    </div>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="tab2">
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
                <form class="form-inline search-from">
                    <div class="form-group">
                        <label>科室名称：</label>
                        <select class="form-control">
                            <option>不分科室</option>
                            <option>骨科</option>
                            <option>妇产科</option>
                            <option>外科</option>
                            <option>内科</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>病区名称：</label>
                        <select class="form-control">
                            <option>不分病区</option>
                            <option>病区一</option>
                            <option>病区二</option>
                            <option>病区三</option>
                            <option>病区四</option>
                            <option>病区五</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>类别：</label>
                        <select class="form-control">
                            <option>不分类别</option>
                            <option>军人</option>
                            <option>地方医保</option>
                            <option>全费</option>
                        </select>
                    </div>
                </form>
                <button type="button" class="btn btn-primary x-btn">查询</button>
                <button type="button" class="btn btn-warning x-btn">导出</button>
                <button id="back-1" type="button" class="btn btn-info x-btn">返回</button>
            </div>
            <div id="panel-6" class="panel">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">全院2015年9月总抗菌药物使用强度</p>

                        <div id="audColumn" class="x-column"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">全院2015年9月各科室总抗菌药物使用强度列表</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>科室名称</th>
                                <th>使用强度</th>
                                <th>目标值</th>
                                <th>排名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a onclick="switchToDepartment('肾病科')">肾病科</a></td>
                                <td>282956</td>
                                <td>363535</td>
                                <td>4455411</td>
                            </tr>
                            </tbody>
                        </table>
                        <ul class="pagination-sm right thandrugs-pagination"></ul>
                    </div>
                </div>
            </div>
            <div id="panel-7" class="panel hide">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">肾病科2015年9月总抗菌药物使用强度</p>

                        <div id="departmentAudColumn" class="x-column"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">肾病科2015年9月各药品抗菌药物使用强度列表</p>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>病区名称</th>
                                <th>使用强度</th>
                                <th>目标值</th>
                                <th>排名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><a onclick="switchToWard()">肾病一区</a></td>
                                <td>282956</td>
                                <td>363535</td>
                                <td>4455411</td>
                            </tr>
                            </tbody>
                        </table>
                        <ul class="pagination-sm right thandrugs-pagination"></ul>
                    </div>
                </div>
            </div>
            <div id="panel-8" class="panel hide">
                <div class="row">
                    <div class="col-md-12">
                        <p class="explanation">肾病科一区2015年9月总抗菌药物使用强度</p>

                        <div id="wardAudColumn" class="x-column"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/static/js/mmi/aud.js"/>"></script>
<script src="<c:url value="/static/js/chart_helper.js"/> "></script>
</body>
</html>
